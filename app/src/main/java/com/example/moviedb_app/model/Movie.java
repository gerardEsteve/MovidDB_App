package com.example.moviedb_app.model;

import android.graphics.Bitmap;
import android.media.Image;

public class Movie {
    //TODO definir la clase

    private String title;
    private Double vote_average;
    private String release_date; // TODO mirar en la api si es STRING o que es
    private String description;
    private Bitmap thumbnail; // drawable ? bitmap ? mirar en la api TODO

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
