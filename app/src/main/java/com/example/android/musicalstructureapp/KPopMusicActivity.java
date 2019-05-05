package com.example.android.musicalstructureapp;

public class KPopMusicActivity extends RecommendedTrackActivity {
    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_KPOP;
    }
}