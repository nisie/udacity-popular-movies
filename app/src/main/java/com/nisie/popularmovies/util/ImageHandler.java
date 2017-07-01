package com.nisie.popularmovies.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nisie.popularmovies.R;


/**
 * @author by natha on 6/24/2017.
 */

public class ImageHandler {
    public static void loadImageFromUrl(ImageView ivMovie, String imgUrl) {
        if (!imgUrl.equals("")) {
            Glide.with(ivMovie.getContext())
                    .load(imgUrl)
                    .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                    .into(ivMovie);
        }
    }
}
