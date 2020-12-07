package com.example.moviedb_app.presenter;

import com.example.moviedb_app.model.Movie;
import com.example.moviedb_app.view.MovieRowView;

import java.util.ArrayList;

public interface MoviesPresenterInterface {

    void getMovies(String query);

    void onBindMovieRowViewAtPosition(int position, MovieRowView holder);

    int getMoviesCount();

    void onApiResultsFinished(ArrayList<Movie> movies, int totalPages);

    void onErrorApiCall(Throwable t);

    void onErrorResponse(String message);

    void movieClicked(int position);
}
