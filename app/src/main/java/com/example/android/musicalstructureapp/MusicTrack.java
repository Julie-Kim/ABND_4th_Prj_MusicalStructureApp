package com.example.android.musicalstructureapp;

class MusicTrack {
    private static final String TAG = "MusicTrack";

    private String mId;
    private String mTitle;
    private String mAlbum;
    private String mArtist;
    private String mData;

    MusicTrack(String id, String title, String album, String artist) {
        mId = id;
        mTitle = title;
        mAlbum = album;
        mArtist = artist;
    }

    MusicTrack(String id, String title, String album, String artist, String data) {
        mId = id;
        mTitle = title;
        mAlbum = album;
        mArtist = artist;
        mData = data;
    }

    String getId() {
        return mId;
    }

    String getTitle() {
        return mTitle;
    }

    String getAlbum() {
        return mAlbum;
    }

    String getArtist() {
        return mArtist;
    }

    String getData() {
        return mData;
    }
}