package com.example.moviedb_app.presenter;

import android.util.Log;

import com.example.moviedb_app.model.Movie;
import com.example.moviedb_app.model.MoviesListImplementation;
import com.example.moviedb_app.model.MoviesListInterface;
import com.example.moviedb_app.view.MovieRowView;
import com.example.moviedb_app.view.MoviesViewInterface;

import java.util.ArrayList;

public class MoviesPresenterImplementation implements MoviesPresenterInterface {

    private final String LOGTAG = "PresenterLog";
    ArrayList<Movie> mMovies;
    MoviesViewInterface moviesViewInterface;
    MoviesListInterface moviesListInterface;

    int page;
    int totalPages;
    String query;

    public MoviesPresenterImplementation(MoviesViewInterface moviesViewInterface) {
        this.mMovies = new ArrayList<>();
        this.moviesViewInterface = moviesViewInterface;
        moviesListInterface = new MoviesListImplementation();
        page = 1;
        totalPages= 0;
        query ="";
    }

    @Override
    public void getMovies(String query) {
        this.query = query;
        this.page = 1;
        moviesViewInterface.showLoading();
        moviesListInterface.getMovies(query, this,page);
    }


    @Override
    public void onBindMovieRowViewAtPosition(int position, MovieRowView holder) {
        if (position == mMovies.size()-3 && page < totalPages){
            ++page;
            Log.i(LOGTAG, "Estoy en la page: "+page + "/"+totalPages);
            moviesListInterface.getMovies(query,this,page);
        }
        Movie movie = mMovies.get(position);
        holder.setTitle(movie.getTitle());
        holder.setVote(movie.getVote_average());
        holder.setImage(movie.getThumbnailPath());
    }

    @Override
    public int getMoviesCount() {
        return mMovies.size();
    }

    @Override
    public void onApiResultsFinished(ArrayList<Movie> movies, int totalPages) {
       // mMovies = movies;
        mMovies.addAll(movies);
        this.totalPages = totalPages;
        if(movies.size()==0) moviesViewInterface.showEmptyScreen();
        else moviesViewInterface.showMovies();
    }

    @Override
    public void onErrorApiCall(Throwable t) {
        moviesViewInterface.showError(t.getMessage());
    }

    @Override
    public void onErrorResponse(String message) {
        moviesViewInterface.showError(message);
    }

    @Override
    public void movieClicked(int position) {
        moviesViewInterface.openMovieDetails(mMovies.get(position).getTitle(),mMovies.get(position).getDescription(),mMovies.get(position).getVote_average(), mMovies.get(position).getThumbnailPath());
    }
}
