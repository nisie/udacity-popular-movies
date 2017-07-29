package com.nisie.popularmovies.movielist.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieReviewViewModel implements Parcelable{
    String author;
    String review;

    public MovieReviewViewModel(String author, String review) {
        this.author = author;
        this.review = review;
    }

    protected MovieReviewViewModel(Parcel in) {
        author = in.readString();
        review = in.readString();
    }

    public static final Creator<MovieReviewViewModel> CREATOR = new Creator<MovieReviewViewModel>() {
        @Override
        public MovieReviewViewModel createFromParcel(Parcel in) {
            return new MovieReviewViewModel(in);
        }

        @Override
        public MovieReviewViewModel[] newArray(int size) {
            return new MovieReviewViewModel[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(review);
    }
}
