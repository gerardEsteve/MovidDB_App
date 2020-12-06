package com.example.moviedb_app.view;

public interface MoviesViewInterface {

    void showMovies();
    void showLoading();
    void showEmptyScreen();
    void showError(String message);
}
