package com.example.android.musicalstructureapp;

public class ClassicMusicActivity extends RecommendedTrackActivity {
    private static final String TAG = "ClassicMusicActivity";

    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_CLASSIC;
    }
}