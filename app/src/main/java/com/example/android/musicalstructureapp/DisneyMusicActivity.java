package com.example.android.musicalstructureapp;

public class DisneyMusicActivity extends RecommendedTrackActivity {
    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_DISNEY;
    }
}