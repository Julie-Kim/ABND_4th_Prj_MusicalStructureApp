package com.example.android.musicalstructureapp;

public class DisneyMusicActivity extends RecommendedTrackActivity {
    private static final String TAG = "DisneyMusicActivity";

    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_DISNEY;
    }
}