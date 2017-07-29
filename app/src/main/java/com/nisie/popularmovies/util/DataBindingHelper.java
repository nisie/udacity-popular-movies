package com.nisie.popularmovies.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nisie.popularmovies.movielist.presentation.model.MovieReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.model.MovieTrailerViewModel;
import com.nisie.popularmovies.movielist.presentation.ui.adapter.ReviewsAdapter;
import com.nisie.popularmovies.movielist.presentation.ui.adapter.TrailerAdapter;

/**
 * @author by nisie on 7/29/17.
 */

public class DataBindingHelper {

    @BindingAdapter("bind:items")
    public static void bindReview(RecyclerView view, ObservableArrayList<MovieReviewViewModel>
            list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new ReviewsAdapter(list));
    }

    @BindingAdapter("bind:items")
    public static void bindTrailer(RecyclerView view, ObservableArrayList<MovieTrailerViewModel>
            list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new TrailerAdapter(list));
    }
}
