package com.example.android.musicalstructureapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

class MusicTrackAdapter extends ArrayAdapter<MusicTrack> {
    private static final String TAG = "MusicTrackAdapter";

    private ArrayList<MusicTrack> mMusicTracks = new ArrayList<>();

    MusicTrackAdapter(Activity context, ArrayList<MusicTrack> musicTracks) {
        super(context, 0, musicTracks);

        setMusicTrackList(musicTracks);
    }

    private void setMusicTrackList(ArrayList<MusicTrack> musicTracks) {
        mMusicTracks.clear();
        mMusicTracks.addAll(musicTracks);

        Log.d(TAG, "setMusicTrackList() size : " + mMusicTracks.size());
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final MusicTrack musicTrack = getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView trackNameTextView = listItemView.findViewById(R.id.track_name_text_view);
        trackNameTextView.setText(musicTrack.getTitle());

        TextView albumNameTextView = listItemView.findViewById(R.id.album_name_text_view);
        albumNameTextView.setText(musicTrack.getAlbum());

        TextView artistNameTextView = listItemView.findViewById(R.id.artist_name_text_view);
        artistNameTextView.setText(musicTrack.getArtist());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlayActivity(position);
            }
        });

        ImageButton playButton = listItemView.findViewById(R.id.list_item_play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlayActivity(position);
            }
        });

        return listItemView;
    }

    private void startPlayActivity(int position) {
        Intent intent = new Intent(getContext(), PlayActivity.class);

        intent.putParcelableArrayListExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_TRACK_LIST, mMusicTracks);
        intent.putExtra(MusicTrackConstant.INTENT_EXTRA_POSITION, position);

        getContext().startActivity(intent);
    }
}
