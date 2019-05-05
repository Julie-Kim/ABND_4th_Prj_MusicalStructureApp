package com.example.android.musicalstructureapp;

public class ClassicMusicActivity extends RecommendedTrackActivity {
    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_CLASSIC;
    }
}