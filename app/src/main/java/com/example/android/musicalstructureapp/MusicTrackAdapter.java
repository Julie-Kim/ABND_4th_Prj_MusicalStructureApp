package com.example.android.musicalstructureapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

class MusicTrackAdapter extends ArrayAdapter<MusicTrack> {
    private static final String TAG = "MusicTrackAdapter";

    MusicTrackAdapter(Activity context, ArrayList<MusicTrack> musicTracks) {
        super(context, 0, musicTracks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

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
                startPlayActivity(musicTrack);
            }
        });

        ImageButton playButton = listItemView.findViewById(R.id.list_item_play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlayActivity(musicTrack);
            }
        });

        return listItemView;
    }

    private void startPlayActivity(MusicTrack musicTrack) {
        Intent intent = new Intent(getContext(), PlayActivity.class);

        intent.putExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_TITLE, musicTrack.getTitle());
        intent.putExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_ALBUM, musicTrack.getAlbum());
        intent.putExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_ARTIST, musicTrack.getArtist());
        intent.putExtra(MusicTrackConstant.INTENT_EXTRA_MUSIC_DATA, musicTrack.getData());

        getContext().startActivity(intent);
    }
}
