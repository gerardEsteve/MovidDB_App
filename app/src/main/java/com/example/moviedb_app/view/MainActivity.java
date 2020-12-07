package com.example.moviedb_app.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.moviedb_app.R;
import com.example.moviedb_app.presenter.MoviesPresenterImplementation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesViewInterface{

    public static final String movieTitleKey ="MOVIE_TITLE";
    public static final String movieRatingKey ="MOVIE_RATING";
    public static final String movieDescKey ="MOVIE_DESC";
    public static final String movieImgPathKey ="MOVIE_PATH";
    public static final String ratingFormat = "Average rating: ";


    @BindView(R.id.recyclerView) RecyclerView moviesRecyclerView;
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.empty) ImageView empty;

    MoviesListAdapter moviesListAdapter;
    MoviesPresenterImplementation moviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_details);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        moviesPresenter = new MoviesPresenterImplementation(this);

        moviesListAdapter = new MoviesListAdapter(moviesPresenter,this);
        moviesRecyclerView.setAdapter(moviesListAdapter);
        //moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        showEmptyScreen();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                moviesPresenter.getMovies(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMovies() {
        progressBar.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
        moviesListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyScreen() {
        moviesListAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError(String message) {
        progressBar.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
        Log.i("MainActivity",message);
    }

    @Override
    public void openMovieDetails(String title, String desc, Double rating, String path) {
        Intent detailIntent = new Intent(this, MovieDetailsActivity.class);
        detailIntent.putExtra(movieDescKey,desc);
        detailIntent.putExtra(movieRatingKey,rating);
        detailIntent.putExtra(movieTitleKey,title);
        detailIntent.putExtra(movieImgPathKey,path);
        startActivity(detailIntent);
    }

}