package com.shivam.wish;

/**
 * Created by Harish Gola on 17-04-2017.
 */


public class Music {
    private String title, album, year, artist, album_art;

    public Music() {
    }

    public Music(String title, String album, String year, String artist) {
        this.title = title;
        this.album = album;
        this.year = year;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArtist() {
        return album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}