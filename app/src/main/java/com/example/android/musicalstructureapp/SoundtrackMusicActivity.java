package com.example.android.musicalstructureapp;

public class SoundtrackMusicActivity extends RecommendedTrackActivity {
    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_SOUNDTRACK;
    }
}