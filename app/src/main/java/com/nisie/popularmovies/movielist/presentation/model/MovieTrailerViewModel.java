package com.nisie.popularmovies.movielist.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieTrailerViewModel implements Parcelable{
    String videoUrl;
    String thumbnailUrl;
    String name;
    String key;

    public MovieTrailerViewModel(String videoUrl, String thumbnailUrl, String name, String key) {
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.name = name;
        this.key = key;
    }

    protected MovieTrailerViewModel(Parcel in) {
        videoUrl = in.readString();
        thumbnailUrl = in.readString();
        name = in.readString();
        key = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoUrl);
        dest.writeString(thumbnailUrl);
        dest.writeString(name);
        dest.writeString(key);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieTrailerViewModel> CREATOR = new Creator<MovieTrailerViewModel>() {
        @Override
        public MovieTrailerViewModel createFromParcel(Parcel in) {
            return new MovieTrailerViewModel(in);
        }

        @Override
        public MovieTrailerViewModel[] newArray(int size) {
            return new MovieTrailerViewModel[size];
        }
    };

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
