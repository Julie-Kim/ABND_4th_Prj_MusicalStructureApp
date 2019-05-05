package com.example.android.musicalstructureapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MyMusicActivity extends AppCompatActivity {
    private static final String TAG = "MyMusicActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.music_track_list);

        // Check runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MusicTrackConstant.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                return;
            }
        }

        getMyMusicTrackList();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MusicTrackConstant.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getMyMusicTrackList();
                }
                break;

            default:
                break;
        }
    }

    private void getMyMusicTrackList() {
        ArrayList<MusicTrack> musicTracks = new ArrayList<>();

        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.AudioColumns._ID,
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.ArtistColumns.ARTIST
        };

        Cursor cursor = resolver.query(uri, projection, null, null, null);
        if (cursor == null) {
            Log.e(TAG, "getMyMusicTrackList() cursor is null.");
            return;
        }

        Log.d(TAG, "getMyMusicTrackList() cursor : " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID));
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.ArtistColumns.ARTIST));

                Log.d(TAG, "title: " + title + ", album: " + album + ", artist: " + artist + ", data: " + data);
                musicTracks.add(new MusicTrack(title, album, artist, data));
            }
        }
        cursor.close();

        setMusicTrackAdapter(musicTracks);
    }

    private void setMusicTrackAdapter(ArrayList<MusicTrack> musicTracks) {
        MusicTrackAdapter adapter = new MusicTrackAdapter(this, musicTracks);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}