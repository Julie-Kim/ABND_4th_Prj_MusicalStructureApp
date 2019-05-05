package com.example.android.musicalstructureapp;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

class MusicTrack implements Parcelable {
    private String mTitle;
    private String mAlbum;
    private String mArtist;
    private String mData;

    MusicTrack(String title, String album, String artist) {
        this(title, album, artist, "");
    }

    MusicTrack(String title, String album, String artist, String data) {
        mTitle = title;
        mAlbum = album;
        mArtist = artist;
        mData = data;
    }

    private MusicTrack(@NotNull Parcel in) {
        mTitle = in.readString();
        mAlbum = in.readString();
        mArtist = in.readString();
        mData = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mAlbum);
        dest.writeString(mArtist);
        dest.writeString(mData);
    }

    public static final Creator<MusicTrack> CREATOR = new Creator<MusicTrack>() {
        @Override
        public MusicTrack createFromParcel(Parcel in) {
            return new MusicTrack(in);
        }

        @Override
        public MusicTrack[] newArray(int size) {
            return new MusicTrack[size];
        }
    };
}