package com.example.android.musicalstructureapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public abstract class RecommendedTrackActivity extends AppCompatActivity {
    private static final String TAG = "RecommendedTrackActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.music_track_list);

        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNetworkErrorToast(this);
            return;
        }

        (new GetMusicTracksTask(this)).execute(getRequest());
    }

    abstract protected String getRequest();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void setMusicTrackAdapter(ArrayList<MusicTrack> musicTracks) {
        MusicTrackAdapter adapter = new MusicTrackAdapter(this, musicTracks);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    protected static class GetMusicTracksTask extends AsyncTask<String, Void, String> {
        private static final String TAG = "GetMusicTracksTask";

        private WeakReference<RecommendedTrackActivity> mActivity;

        GetMusicTracksTask(RecommendedTrackActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        protected String doInBackground(String... params) {
            String request = params[0];
            String result = "";

            try {
                URL url = new URL(MusicTrackConstant.API_URL + request + MusicTrackConstant.API_KEY);
                Log.d(TAG, "url: " + url);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder response = new StringBuilder();

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    result = response.toString();
                    Log.d(TAG, "result: " + result);

                    bufferedReader.close();
                } else {
                    Log.e(TAG, "doInBackground() connection error : " + connection.getResponseCode());
                }
            } catch (MalformedURLException e) {
                Log.e(TAG, "doInBackground() error : " + e);
            } catch (IOException e) {
                Log.e(TAG, "doInBackground() error : " + e);
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            ArrayList<MusicTrack> musicTracks = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject messageObject = jsonObject.getJSONObject("message");
                JSONObject bodyObject = messageObject.getJSONObject("body");
                JSONArray trackArray = bodyObject.getJSONArray("track_list");

                for (int i = 0; i < trackArray.length(); i++) {
                    JSONObject trackObject = trackArray.getJSONObject(i).getJSONObject("track");
                    String id = trackObject.getString("track_id");
                    String title = trackObject.getString("track_name");
                    String album = trackObject.getString("album_name");
                    String artist = trackObject.getString("artist_name");

                    Log.d(TAG, "[" + i + "]" + " id: " + id + ", title: " + title + ", album: " + album + ", artist: " + artist);
                    musicTracks.add(new MusicTrack(id, title, album, artist));
                }

                mActivity.get().setMusicTrackAdapter(musicTracks);
            } catch (JSONException e) {
                Log.e(TAG, "onPostExecute() json parsing error : " + e);
            }
        }
    }
}