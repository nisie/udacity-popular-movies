package com.nisie.popularmovies.movielist.domain.repository;

import com.nisie.popularmovies.movielist.domain.mapper.MovieListMapper;
import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.domain.network.service.MovieService;

import java.util.Map;

import rx.Observable;


/**
 * @author by natha on 6/26/2017.
 */

public class MovieListRepositoryImpl implements MovieListRepository {

    private final MovieService movieService;
    private final MovieListMapper movieListMapper;

    public MovieListRepositoryImpl(MovieService movieService,
                                   MovieListMapper movieListMapper) {
        this.movieService = movieService;
        this.movieListMapper = movieListMapper;
    }

    @Override
    public Observable<MovieResultDomain> getMovies(Map<String, Object> params) {
        return movieService.getApi()
                .getMovieList(params)
                .map(movieListMapper);
    }

    @Override
    public Observable<MovieResultDomain> getMostPopularMovies(Map<String, Object> params) {
        return  movieService.getApi()
                .getMostPopularMovie(params)
                .map(movieListMapper);
    }

    @Override
    public Observable<MovieResultDomain> getHighestRatedMovies(Map<String, Object> params) {
        return  movieService.getApi()
                .getTopRatedMovie(params)
                .map(movieListMapper);
    }
}
