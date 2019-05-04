package com.example.android.musicalstructureapp;

public class PopRockMusicActivity extends RecommendedTrackActivity {
    private static final String TAG = "PopRockMusicActivity";

    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_POP_ROCK;
    }
}