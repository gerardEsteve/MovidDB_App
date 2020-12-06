package com.example.moviedb_app.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Movie {

    private String title;
    private Double vote_average;
    private String release_date;

    @SerializedName("overview")
    private String description;

    @SerializedName("poster_path")
    private String thumbnailPath;

    public Movie(String title, Double vote_average, String release_date, String description, String thumbnailPath) {
        this.title = title;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.description = description;
        this.thumbnailPath = thumbnailPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
