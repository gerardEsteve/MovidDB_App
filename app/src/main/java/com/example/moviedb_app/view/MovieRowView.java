package com.example.moviedb_app.view;

import android.graphics.Bitmap;
import android.media.Image;

public interface MovieRowView {

    public void setTitle(String title);
    public void setImage(Bitmap image);
    public void setVote(Double vote);


}
