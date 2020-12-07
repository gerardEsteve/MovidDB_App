package com.example.moviedb_app.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search/movie")
    Call<MoviesListedRes> getMoviesByQuery(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);

}
