package com.example.android.musicalstructureapp;

public class PopRockMusicActivity extends RecommendedTrackActivity {
    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_POP_ROCK;
    }
}