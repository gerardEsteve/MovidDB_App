package com.example.moviedb_app.view;

import com.example.moviedb_app.model.Movie;

import java.util.List;

public interface MoviesViewInterface {

    void showMovies();
    void showLoading();
    void showEmptyScreen();
    void showError(String message);
}
