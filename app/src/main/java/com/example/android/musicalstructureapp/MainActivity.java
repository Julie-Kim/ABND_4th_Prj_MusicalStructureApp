package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myMusic = findViewById(R.id.my_music);
        myMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyMusicActivity.class);
                startActivity(intent);
            }
        });

        TextView kPopMusic = findViewById(R.id.kpop_music);
        kPopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KPopMusicActivity.class);
                startActivity(intent);
            }
        });

        TextView popRockMusic = findViewById(R.id.pop_rock_music);
        popRockMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PopRockMusicActivity.class);
                startActivity(intent);
            }
        });

        TextView soundtrackMusic = findViewById(R.id.soundtrack_music);
        soundtrackMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SoundtrackMusicActivity.class);
                startActivity(intent);
            }
        });

        TextView classicMusic = findViewById(R.id.classic_music);
        classicMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClassicMusicActivity.class);
                startActivity(intent);
            }
        });

        TextView newAgeMusic = findViewById(R.id.new_age_music);
        newAgeMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewAgeMusicActivity.class);
                startActivity(intent);
            }
        });

        TextView disneyMusic = findViewById(R.id.disney_music);
        disneyMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisneyMusicActivity.class);
                startActivity(intent);
            }
        });
    }
}