package com.nisie.popularmovies.movielist.presentation.model;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieReviewViewModel {
    String author;
    String review;

    public MovieReviewViewModel(String author, String review) {
        this.author = author;
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }
}
