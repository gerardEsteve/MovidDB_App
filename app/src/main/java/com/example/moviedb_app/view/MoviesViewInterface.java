package com.example.moviedb_app.view;

public interface MoviesViewInterface {

    void showMovies();
    void showLoading();
    void showEmptyScreen();
    void showError(String message);
    void openMovieDetails(String title, String desc, Double rating, String path);
}
