package com.example.moviedb_app.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.moviedb_app.R;
import com.example.moviedb_app.network.ApiRetrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.image_details) ImageView imageDetails;
    @BindView(R.id.desc_details) TextView descriptionDetails;
    @BindView(R.id.rating_details) TextView ratingDetails;
    @BindView(R.id.title_details) TextView titleDetails;
    @BindView(R.id.progress_bar_details) ProgressBar progressBar;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;

    private String ratingFormat = "Average rating: ";
    private String imagePath="";
    private String movieTitle="";

    private final String storagePermission = "android.permission.WRITE_EXTERNAL_STORAGE" ;
    private final int storageCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);




        Intent intent = getIntent();
        movieTitle = intent.getStringExtra(MainActivity.movieTitleKey);
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
        titleDetails.setText(movieTitle);
        ratingDetails.setText(ratingFormated);

        imagePath = ApiRetrofit.IMAGE_BIG_BASE_URL + path;

        Glide.with(this)
                .load(imagePath)
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


        floatingActionButton.setOnClickListener(this);


    }



    public boolean checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MovieDetailsActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MovieDetailsActivity.this, new String[] { permission }, requestCode);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == storageCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                shareImageGlide();
            }
            else {
                Toast.makeText(this,"Storage permission needed to share images",Toast.LENGTH_LONG).show();
            }
        }
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

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.fab){

            boolean hasStoragePermission = checkPermission(storagePermission ,storageCode);

            if(hasStoragePermission){
                shareImageGlide();
            }
        }

    }

    public void shareImageGlide(){
        Glide.with(getApplicationContext())
                .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(imagePath)
                .into(new SimpleTarget<Bitmap>(250, 250) {
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, movieTitle);
                        String path = MediaStore.Images.Media.insertImage(getContentResolver(), resource, "", null);


                        Uri screenshotUri = Uri.parse(path);



                        intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        intent.setType("image/*");

                        startActivity(Intent.createChooser(intent, "Share image via..."));
                    }
                });
    }


}