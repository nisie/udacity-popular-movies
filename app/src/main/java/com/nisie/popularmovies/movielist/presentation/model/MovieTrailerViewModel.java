package com.nisie.popularmovies.movielist.presentation.model;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieTrailerViewModel {
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
