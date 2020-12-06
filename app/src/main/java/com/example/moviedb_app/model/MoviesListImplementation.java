package com.example.moviedb_app.model;

import com.example.moviedb_app.model.MoviesListInterface;
import com.example.moviedb_app.network.ApiInterface;
import com.example.moviedb_app.network.ApiRetrofit;
import com.example.moviedb_app.network.MoviesListedRes;
import com.example.moviedb_app.presenter.MoviesPresenterImplementation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moviedb_app.network.ApiRetrofit.API_KEY;

public class MoviesListImplementation implements MoviesListInterface {

    @Override
    public void getMovies(String searchText, MoviesPresenterImplementation moviesPresenter) {
        //TODO

        ApiInterface apiService = ApiRetrofit.getRetrofitClient().create(ApiInterface.class);

        Call<MoviesListedRes> call = apiService.getMoviesByQuery(API_KEY, searchText);
        call.enqueue(new Callback<MoviesListedRes>() {
            @Override
            public void onResponse(Call<MoviesListedRes> call, Response<MoviesListedRes> response) {
                if (response.code() == 200){
                    ArrayList<Movie> movies = new ArrayList<>(response.body().getResults());
                    moviesPresenter.onApiResultsFinished(movies);
                }
                else moviesPresenter.onErrorResponse(response.message());
            }

            @Override
            public void onFailure(Call<MoviesListedRes> call, Throwable t) {
            moviesPresenter.onErrorApiCall(t);
            }
        });
        
    }
}
