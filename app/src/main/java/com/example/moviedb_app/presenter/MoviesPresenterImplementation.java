package com.example.moviedb_app.presenter;

import com.example.moviedb_app.model.Movie;
import com.example.moviedb_app.model.MoviesListImplementation;
import com.example.moviedb_app.model.MoviesListInterface;
import com.example.moviedb_app.view.MovieRowView;
import com.example.moviedb_app.view.MoviesViewInterface;

import java.util.ArrayList;
import java.util.List;

public class MoviesPresenterImplementation implements MoviesPresenterInterface {

    ArrayList<Movie> mMovies;
    MoviesViewInterface moviesViewInterface;
    MoviesListInterface moviesListInterface;


    public MoviesPresenterImplementation(MoviesViewInterface moviesViewInterface) {
        this.mMovies = new ArrayList<>();
        this.moviesViewInterface = moviesViewInterface;
        moviesListInterface = new MoviesListImplementation();
    }

    @Override
    public void getMovies() {
        // TODO  modificar string
        moviesListInterface.getMovies("Avengers", this);
        //setDummyMovies(10);
    }

    /*private void setDummyMovies(int numMovies) {
        for (int i = 0; i < numMovies; ++i){
            Movie movie = new Movie();
            movie.setTitle("title temp "+i);
            movie.setVote_average(Double.valueOf(i));
            mMovies.add(movie);
        }
        String s = "";
    }*/

    @Override
    public void onBindMovieRowViewAtPosition(int position, MovieRowView holder) {
        Movie movie = mMovies.get(position);
        holder.setTitle(movie.getTitle());
        holder.setVote(movie.getVote_average());
        // TODO holder.setImage(movie.getThumbnail());
    }

    @Override
    public int getMoviesCount() {
        return mMovies.size();
    }

    @Override
    public void onApiResultsFinished(ArrayList<Movie> movies) {
        mMovies = movies;
        moviesViewInterface.showMovies();
    }

    @Override
    public void onErrorApiCall(Throwable t) {
        moviesViewInterface.showError(t.getMessage());
    }

    @Override
    public void onErrorResponse(String message) {
        moviesViewInterface.showError(message);
    }
}
