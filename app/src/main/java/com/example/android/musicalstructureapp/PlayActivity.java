package com.example.android.musicalstructureapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {
    private static final String TAG = "PlayActivity";

    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.activity_play);

        setMusicTitle();
        setMusicAlbum();
        setMusicArtist();
        setMusicData();
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

    private void setMusicTitle() {
        String musicTitle = getIntent().getStringExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_TITLE);
        TextView titleText = findViewById(R.id.title_text_view);
        titleText.setText(musicTitle);
    }

    private void setMusicAlbum() {
        String musicAlbum = getIntent().getStringExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_ALBUM);
        TextView titleText = findViewById(R.id.album_text_view);
        titleText.setText(musicAlbum);
    }

    private void setMusicArtist() {
        String musicArtist = getIntent().getStringExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_ARTIST);
        TextView titleText = findViewById(R.id.artist_text_view);
        titleText.setText(musicArtist);
    }

    private void setMusicData() {
        String musicData = getIntent().getStringExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_DATA);
        if (TextUtils.isEmpty(musicData)) {
            Toast.makeText(this, R.string.no_music_data, Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "Music Data : " + musicData);
        if (!prepareMusic(musicData)) {
            Toast.makeText(this, R.string.media_player_error, Toast.LENGTH_SHORT).show();
            return;
        }

        final ImageButton playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer.isPlaying()) {
                    pauseMusic();
                    playButton.setImageResource(R.drawable.play_button);
                } else {
                    playMusic();
                    playButton.setImageResource(R.drawable.pause_button);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        releaseMediaPlayer();
    }

    private boolean prepareMusic(String data) {
        if (data == null) {
            return false;
        }

        try {
            mMediaPlayer.setDataSource(data);
            mMediaPlayer.prepare();
        } catch (Exception e) {
            Log.e(TAG, "prepareMusic() e = " + e);
            return false;
        }
        return true;
    }

    private void playMusic() {
        mMediaPlayer.start();
    }

    private void pauseMusic() {
        mMediaPlayer.pause();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }
}