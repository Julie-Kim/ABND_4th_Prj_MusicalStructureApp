package com.example.android.musicalstructureapp;

public class NewAgeMusicActivity extends RecommendedTrackActivity {
    @Override
    protected String getRequest() {
        return MusicTrackConstant.SEARCH_NEW_AGE;
    }
}