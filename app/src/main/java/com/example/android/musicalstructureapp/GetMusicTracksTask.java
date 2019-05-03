package com.example.android.musicalstructureapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetMusicTracksTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetMusicTracksTask";

    private static final String API_URL = "https://api.musixmatch.com/ws/1.1/";
    private static final String API_KEY = "&apikey=bfe1df5462f32f4f62fc087ad67bf06e";

    @Override
    protected String doInBackground(String... params) {
        String request = params[0];
        String result = "";

        try {
            URL url = new URL(API_URL + request + API_KEY);
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
                Log.e(TAG, "connection error : " + connection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}