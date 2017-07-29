package com.nisie.popularmovies.movielist.presentation.model;

import android.databinding.ObservableArrayList;

/**
 * @author by nisie on 7/29/17.
 */

public class ReviewViewModel {
    private ObservableArrayList<MovieReviewViewModel> listReview = new ObservableArrayList<>();

    public ReviewViewModel() {
        this.listReview = new ObservableArrayList<>();
    }

    public ReviewViewModel(ObservableArrayList<MovieReviewViewModel> listReview) {
        this.listReview = listReview;
    }

    public ObservableArrayList<MovieReviewViewModel> getListReview() {
        return listReview;
    }

    public void setReviewList(ObservableArrayList<MovieReviewViewModel> reviewList) {
        this.listReview.clear();
        this.listReview.addAll(reviewList);
    }
}
