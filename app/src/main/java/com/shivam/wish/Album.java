package com.shivam.wish;

/**
 * Created by Harish Gola on 07-06-2017.
 */

public class Album {
    private String album, album_art, artist;

    public Album(String album, String album_art, String artist) {
        this.album = album;
        this.album_art = album_art;
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String name) {
        this.album = name;
    }

    public String getAlbum_art() {
        return album_art;
    }

    public void setAlbum_art(String album_art) {
        this.album_art = album_art;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


}
