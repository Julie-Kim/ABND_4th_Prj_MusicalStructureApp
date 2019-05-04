package com.example.android.musicalstructureapp;

public class SoundtrackMusicActivity extends RecommendedTrackActivity {
    private static final String TAG = "SoundtrackMusicActivity";

    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_SOUNDTRACK;
    }
}