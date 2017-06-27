package com.nisie.popularmovies.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nisie.popularmovies.R;


/**
 * @author by natha on 6/24/2017.
 */

public class ImageHandler {
    public static void loadImageFromUrl(ImageView ivMovie, String imgUrl) {
        if (!imgUrl.equals("")) {
            Glide.with(ivMovie.getContext())
                    .load(imgUrl)
                    .error(R.mipmap.ic_launcher_round)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivMovie);
        }
    }
}
