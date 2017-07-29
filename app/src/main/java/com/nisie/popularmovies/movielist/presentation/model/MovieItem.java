package com.nisie.popularmovies.movielist.presentation.model;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author by natha on 6/26/2017.
 */

public class MovieItem implements Parcelable {
    private String imgUrl;
    private String title;
    private String releaseDate;
    private float rating;
    private String ratingText;
    private String synopsis;
    private int id;
    private ObservableArrayList<MovieReviewViewModel> listReview = new ObservableArrayList<>();
    private ObservableArrayList<MovieTrailerViewModel> listTrailer = new ObservableArrayList<>();


    public MovieItem(int id, String imgUrl, String title, String releaseDate, float rating,
                     String synopsis) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.ratingText = String.valueOf(rating);
        this.synopsis = synopsis;
        this.listReview = new ObservableArrayList<>();
        this.listTrailer = new ObservableArrayList<>();
    }

    protected MovieItem(Parcel in) {
        imgUrl = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        rating = in.readFloat();
        ratingText = in.readString();
        synopsis = in.readString();
        id = in.readInt();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    public void setListReview(ObservableArrayList<MovieReviewViewModel> listReview) {
        this.listReview.clear();
        this.listReview.addAll(listReview);
    }

    public void setListTrailer(ObservableArrayList<MovieTrailerViewModel> listTrailer) {
        this.listTrailer.clear();
        this.listTrailer.addAll(listTrailer);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getId() {
        return id;
    }

    public ObservableArrayList<MovieReviewViewModel> getListReview() {
        return listReview;
    }

    public ObservableArrayList<MovieTrailerViewModel> getListTrailer() {
        return listTrailer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imgUrl);
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeFloat(rating);
        parcel.writeString(ratingText);
        parcel.writeString(synopsis);
        parcel.writeInt(id);
    }
}
