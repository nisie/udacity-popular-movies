package com.nisie.popularmovies.movielist.domain.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieTrailerResult {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("results")
    @Expose
    private List<MovieTrailerPojo> movieTrailerPojos = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieTrailerPojo> getMovieTrailerPojos() {
        return movieTrailerPojos;
    }

    public void setMovieTrailerPojos(List<MovieTrailerPojo> movieTrailerPojos) {
        this.movieTrailerPojos = movieTrailerPojos;
    }
}
