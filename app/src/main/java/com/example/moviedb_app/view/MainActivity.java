package com.example.moviedb_app.view;

import android.os.Bundle;

import com.example.moviedb_app.R;
import com.example.moviedb_app.model.Movie;
import com.example.moviedb_app.presenter.MoviesPresenterImplementation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesViewInterface{


    @BindView(R.id.recyclerView) RecyclerView moviesRecyclerView;
    MoviesListAdapter moviesListAdapter;
    MoviesPresenterImplementation moviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        moviesPresenter = new MoviesPresenterImplementation(this);

        moviesListAdapter = new MoviesListAdapter(moviesPresenter);
        moviesRecyclerView.setAdapter(moviesListAdapter);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        moviesPresenter.getMovies();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMovies() {
        moviesListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        //TODO
    }

    @Override
    public void showEmptyScreen() {
        //TODO
    }

    @Override
    public void showError(String message) {
        //TODO
        String s = "";
    }
}