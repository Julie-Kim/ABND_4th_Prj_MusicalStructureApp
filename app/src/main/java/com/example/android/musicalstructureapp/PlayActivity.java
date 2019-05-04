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

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    private static final String TAG = "PlayActivity";

    private MediaPlayer mMediaPlayer;
    private ArrayList<MusicTrack> mMusicTracks = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.activity_play);

        if (getIntent() != null) {
            mMusicTracks = getIntent().getParcelableArrayListExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_TRACK_LIST);
            setMusicInfo(getIntent().getIntExtra(MusicTrackConstant.INTENT_EXTRA_POSITION, -1));
        }
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

    private void setMusicInfo(int position) {
        if (position < 0 || position >= mMusicTracks.size()) {
            return;
        }

        MusicTrack musicTrack = mMusicTracks.get(position);

        TextView titleText = findViewById(R.id.title_text_view);
        titleText.setText(musicTrack.getTitle());

        TextView albumText = findViewById(R.id.album_text_view);
        albumText.setText(musicTrack.getAlbum());

        TextView artistText = findViewById(R.id.artist_text_view);
        artistText.setText(musicTrack.getArtist());

        setMusicData(musicTrack.getData(), position);
    }

    private void setMusicData(String musicData, final int position) {
        final ImageButton playButton = findViewById(R.id.play_button);
        final ImageButton prevButton = findViewById(R.id.prev_track_button);
        final ImageButton nextButton = findViewById(R.id.next_track_button);

        Log.d(TAG, "setMusicData() position: " + position + ", data : " + musicData);
        if (isInvalidMusicData(musicData)) {
            playButton.setEnabled(false);
        } else {
            playButton.setImageResource(R.drawable.play_button);
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

        prevButton.setEnabled(position > 0);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMusicInfo(position - 1);
            }
        });

        nextButton.setEnabled(position < mMusicTracks.size() - 1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMusicInfo(position + 1);
            }
        });
    }

    private boolean isInvalidMusicData(String musicData) {
        if (TextUtils.isEmpty(musicData)) {
            Toast.makeText(this, R.string.no_music_data, Toast.LENGTH_SHORT).show();
            return true;
        }

        if (!prepareMusic(musicData)) {
            Toast.makeText(this, R.string.media_player_error, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        releaseMediaPlayer();
    }

    private boolean prepareMusic(String data) {
        if (TextUtils.isEmpty(data)) {
            return false;
        }

        try {
            releaseMediaPlayer();
            mMediaPlayer = new MediaPlayer();

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
            mMediaPlayer = null;
        }
    }
}