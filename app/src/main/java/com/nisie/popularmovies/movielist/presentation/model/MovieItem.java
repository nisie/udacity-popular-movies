package com.nisie.popularmovies.movielist.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

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

    public MovieItem(String imgUrl, String title, String releaseDate, float rating, String synopsis) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.ratingText = String.valueOf(rating);
        this.synopsis = synopsis;
    }

    protected MovieItem(Parcel in) {
        imgUrl = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        rating = in.readFloat();
        ratingText= in.readString();
        synopsis = in.readString();
    }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

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
    }
}
