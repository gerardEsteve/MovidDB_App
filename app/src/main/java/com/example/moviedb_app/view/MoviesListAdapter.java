package com.example.moviedb_app.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.moviedb_app.R;
import com.example.moviedb_app.model.Movie;
import com.example.moviedb_app.network.ApiRetrofit;
import com.example.moviedb_app.presenter.MoviesPresenterImplementation;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {


    private final MoviesPresenterImplementation presenter;
    private Activity mainActivity;

    public MoviesListAdapter(MoviesPresenterImplementation moviesPresenterImplementation, Activity mainActivity) {
        this.presenter = moviesPresenterImplementation;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MoviesListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        presenter.onBindMovieRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
         return presenter.getMoviesCount();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements MovieRowView{

        TextView titleTextView;
        TextView voteTextView;
        ImageView imageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
           // ButterKnife.bind(itemView);
            titleTextView = itemView.findViewById(R.id.movie_title);
            voteTextView = itemView.findViewById(R.id.vote_average);
            imageView = itemView.findViewById(R.id.thumbnail);
        }

        @Override
        public void setTitle(String title) {
            titleTextView.setText(title);
        }

        @Override
        public void setImage(String path) {

            Glide.with(mainActivity)
                    .load(ApiRetrofit.IMAGE_BASE_URL + path)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .apply(new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder))
                    .into(imageView);

        }

        @Override
        public void setVote(Double vote) {
            voteTextView.setText(vote.toString());
        }
    }

}
