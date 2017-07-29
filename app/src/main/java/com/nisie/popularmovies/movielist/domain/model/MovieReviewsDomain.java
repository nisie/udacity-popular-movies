package com.nisie.popularmovies.movielist.domain.model;

import java.util.List;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieReviewsDomain {

    private int id;
    private int page;
    private List<MovieReviewItemDomain> results = null;
    private int totalPages;
    private int totalResults;

    public MovieReviewsDomain(int id, int page, List<MovieReviewItemDomain> results,
                              int totalPages, int totalResults) {
        this.id = id;
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public List<MovieReviewItemDomain> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
