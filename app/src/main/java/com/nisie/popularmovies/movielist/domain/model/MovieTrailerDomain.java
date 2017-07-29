package com.nisie.popularmovies.movielist.domain.model;

import java.util.List;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieTrailerDomain {

    private int id;
    private List<MovieTrailerItemDomain> listTrailer = null;

    public MovieTrailerDomain(int id, List<MovieTrailerItemDomain> listTrailer) {
        this.id = id;
        this.listTrailer = listTrailer;
    }

    public int getId() {
        return id;
    }

    public List<MovieTrailerItemDomain> getListTrailer() {
        return listTrailer;
    }
}
