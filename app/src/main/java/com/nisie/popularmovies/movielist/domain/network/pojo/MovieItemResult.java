package com.nisie.popularmovies.movielist.domain.network.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author by natha on 6/26/2017.
 */


public class MovieItemResult {

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<MovieItemPojo> movieItemPojos = null;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieItemPojo> getMovieItemPojos() {
        return movieItemPojos;
    }

    public void setMovieItemPojos(List<MovieItemPojo> movieItemPojos) {
        this.movieItemPojos = movieItemPojos;
    }

}
