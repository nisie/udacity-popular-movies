package com.nisie.popularmovies.movielist.domain.repository;

import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;

import java.util.Map;

import rx.Observable;

/**
 * @author by natha on 6/26/2017.
 */

public interface MovieListRepository {

    Observable<MovieResultDomain> getMovies(Map<String, Object> params);

    Observable<MovieResultDomain> getMostPopularMovies(Map<String, Object> params);

    Observable<MovieResultDomain> getHighestRatedMovies(Map<String, Object> params);

}
