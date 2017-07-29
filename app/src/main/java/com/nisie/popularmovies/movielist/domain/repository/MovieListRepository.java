package com.nisie.popularmovies.movielist.domain.repository;

import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieReviewsDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerDomain;

import java.util.Map;

import rx.Observable;

/**
 * @author by natha on 6/26/2017.
 */

public interface MovieListRepository {

    Observable<MovieResultDomain> getMovies(Map<String, Object> params);

    Observable<MovieResultDomain> getMostPopularMovies(Map<String, Object> params);

    Observable<MovieResultDomain> getHighestRatedMovies(Map<String, Object> params);

    Observable<MovieTrailerDomain> getMovieTrailers(Map<String, Object> requestParams);

    Observable<MovieReviewsDomain> getMovieReviews(Map<String, Object> requestParams);
}
