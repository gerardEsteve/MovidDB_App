package com.example.moviedb_app.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.moviedb_app.R;
import com.example.moviedb_app.network.ApiRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.image_details) ImageView imageDetails;
    @BindView(R.id.desc_details) TextView descriptionDetails;
    @BindView(R.id.rating_details) TextView ratingDetails;
    @BindView(R.id.title_details) TextView titleDetails;
    @BindView(R.id.progress_bar_details) ProgressBar progressBar;

    private String ratingFormat = "Average rating: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.movieTitleKey);
        Double rating = intent.getDoubleExtra(MainActivity.movieRatingKey,0.0);
        String description = intent.getStringExtra(MainActivity.movieDescKey);
        String path = intent.getStringExtra(MainActivity.movieImgPathKey);

        Toolbar toolbar = findViewById(R.id.toolbar_details);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        descriptionDetails.setText(description);
        String ratingFormated = ratingFormat + rating.toString() +"/10";
        titleDetails.setText(title);
        ratingDetails.setText(ratingFormated);


        Glide.with(this)
                .load(ApiRetrofit.IMAGE_BIG_BASE_URL + path)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder))
                .into(imageDetails);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}