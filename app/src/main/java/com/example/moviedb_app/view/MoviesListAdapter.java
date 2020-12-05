package com.example.moviedb_app.view;

import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb_app.R;
import com.example.moviedb_app.model.Movie;
import com.example.moviedb_app.presenter.MoviesPresenterImplementation;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {


    private final MoviesPresenterImplementation presenter;

    public MoviesListAdapter(MoviesPresenterImplementation moviesPresenterImplementation) {
        this.presenter = moviesPresenterImplementation;
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

       /* @BindView(R.id.movie_title) TextView titleTextView;
        @BindView(R.id.vote_average) TextView voteTextView;
        @BindView(R.id.thumbnail) ImageView imageView;*/

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
        public void setImage(Bitmap image) {
            imageView.setImageBitmap(image);
        }

        @Override
        public void setVote(Double vote) {
            voteTextView.setText(vote.toString());
        }
    }

}
