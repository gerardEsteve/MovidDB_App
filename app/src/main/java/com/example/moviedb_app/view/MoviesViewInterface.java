package com.example.moviedb_app.view;

import com.example.moviedb_app.model.Movie;

import java.util.ArrayList;

public interface MoviesViewInterface {

    public void showMovies(ArrayList<Movie> movies);
    public void showLoading();
    public void showEmptyScreen();

}
