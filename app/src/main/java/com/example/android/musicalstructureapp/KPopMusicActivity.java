package com.example.android.musicalstructureapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class KPopMusicActivity extends AppCompatActivity {
    private static final String TAG = "KPopMusicActivity";

    private static final String GET_KR_TRACKS = "track.search?q=&f_music_genre_id=1686&page_size=20&page=1&s_track_rating=desc";

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

        (new GetKPopMusicTracksTask(this)).execute(GET_KR_TRACKS);
    }

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

    private void setMusicTrackAdapter(ArrayList<MusicTrack> musicTracks) {
        MusicTrackAdapter adapter = new MusicTrackAdapter(this, musicTracks);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    private static class GetKPopMusicTracksTask extends GetMusicTracksTask {
        private WeakReference<KPopMusicActivity> mActivity;

        GetKPopMusicTracksTask(KPopMusicActivity activity) {
            mActivity = new WeakReference<>(activity);
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
                Log.e(TAG, "json parsing error : " + e);
            }
        }
    }
}