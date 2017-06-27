package com.nisie.popularmovies.movielist.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * @author by natha on 6/24/2017.
 */

public class MovieResultDomain{
    private int page;
    private int totalResults;
    private int totalPages;
    private List<MovieItemDomain> results = null;

    public MovieResultDomain(int page, int totalResults, int totalPages, List<MovieItemDomain> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

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

    public List<MovieItemDomain> getResults() {
        return results;
    }

    public void setResults(List<MovieItemDomain> results) {
        this.results = results;
    }
}
