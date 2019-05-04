package com.example.android.musicalstructureapp;

public class NewAgeMusicActivity extends RecommendedTrackActivity {
    private static final String TAG = "NewAgeMusicActivity";

    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_NEW_AGE;
    }
}