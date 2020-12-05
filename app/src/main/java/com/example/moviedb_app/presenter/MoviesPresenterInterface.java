package com.example.moviedb_app.presenter;

import com.example.moviedb_app.view.MovieRowView;
import com.example.moviedb_app.view.MoviesListAdapter;

public interface MoviesPresenterInterface {

    public void getMovies();

    void onBindMovieRowViewAtPosition(int position, MovieRowView holder);

    int getMoviesCount();
}
