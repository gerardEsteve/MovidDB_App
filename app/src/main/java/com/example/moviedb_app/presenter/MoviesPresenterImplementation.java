package com.example.moviedb_app.presenter;

import com.example.moviedb_app.model.Movie;
import com.example.moviedb_app.view.MovieRowView;
import com.example.moviedb_app.view.MoviesListAdapter;
import com.example.moviedb_app.view.MoviesViewInterface;

import java.util.ArrayList;
import java.util.Random;

public class MoviesPresenterImplementation implements MoviesPresenterInterface {

    ArrayList<Movie> mMovies;
    MoviesViewInterface moviesViewInterface;


    public MoviesPresenterImplementation(MoviesViewInterface moviesViewInterface) {
        this.mMovies = new ArrayList<>();
        this.moviesViewInterface = moviesViewInterface;
    }

    @Override
    public void getMovies() {
        // TODO  asyncTask para get movies del network
        setDummyMovies(10);
        moviesViewInterface.showMovies();
    }

    private void setDummyMovies(int numMovies) {
        for (int i = 0; i < numMovies; ++i){
            Movie movie = new Movie();
            movie.setTitle("title temp "+i);
            movie.setVote_average(Double.valueOf(i));
            mMovies.add(movie);
        }
        String s = "";
    }

    @Override
    public void onBindMovieRowViewAtPosition(int position, MovieRowView holder) {
        Movie movie = mMovies.get(position);
        holder.setTitle(movie.getTitle());
        holder.setVote(movie.getVote_average());
        holder.setImage(movie.getThumbnail());
    }

    @Override
    public int getMoviesCount() {
        return mMovies.size();
    }
}
