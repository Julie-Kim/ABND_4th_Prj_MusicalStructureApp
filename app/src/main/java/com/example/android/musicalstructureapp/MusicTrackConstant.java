package com.example.android.musicalstructureapp;

class MusicTrackConstant {

    static final String API_URL = "http://api.musixmatch.com/ws/1.1/";
    static final String API_KEY = "&apikey=bfe1df5462f32f4f62fc087ad67bf06e";

    private static final String MUSIC_GENRE_ID_KPOP = "1686";
    private static final String MUSIC_GENRE_ID_POP_ROCK = "1133";
    private static final String MUSIC_GENRE_ID_SOUNDTRACK = "1169";
    private static final String MUSIC_GENRE_ID_CLASSIC = "5";
    private static final String MUSIC_GENRE_ID_NEW_AGE = "13";
    private static final String MUSIC_GENRE_ID_DISNEY = "50000063";

    static final String SEARCH_KPOP = "track.search?q=&f_music_genre_id=" + MUSIC_GENRE_ID_KPOP + "&page_size=20&page=1&s_track_rating=desc";
    static final String SEARCH_POP_ROCK = "track.search?q=&f_music_genre_id=" + MUSIC_GENRE_ID_POP_ROCK + "&page_size=20&page=1&s_track_rating=desc";
    static final String SEARCH_SOUNDTRACK = "track.search?q=&f_music_genre_id=" + MUSIC_GENRE_ID_SOUNDTRACK + "&page_size=20&page=1&s_track_rating=desc";
    static final String SEARCH_CLASSIC = "track.search?q=&f_music_genre_id=" + MUSIC_GENRE_ID_CLASSIC + "&page_size=20&page=1&s_track_rating=desc";
    static final String SEARCH_NEW_AGE = "track.search?q=&f_music_genre_id=" + MUSIC_GENRE_ID_NEW_AGE + "&page_size=20&page=1&s_track_rating=desc";
    static final String SEARCH_DISNEY = "track.search?q=&f_music_genre_id=" + MUSIC_GENRE_ID_DISNEY + "&page_size=20&page=1&s_track_rating=desc";

    static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 10;

    static final String INTENT_EXTRA_MUSIC_TRACK_LIST = "music_track_list";
    static final String INTENT_EXTRA_POSITION = "position";

}