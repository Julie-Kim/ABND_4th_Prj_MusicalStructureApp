package com.example.android.musicalstructureapp;

public class KPopMusicActivity extends RecommendedTrackActivity {
    private static final String TAG = "KPopMusicActivity";

    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_KPOP;
    }
}