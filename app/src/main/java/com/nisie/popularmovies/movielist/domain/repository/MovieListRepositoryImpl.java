package com.nisie.popularmovies.movielist.domain.repository;

import com.nisie.popularmovies.movielist.domain.interactor.GetMovieTrailerUseCase;
import com.nisie.popularmovies.movielist.domain.mapper.MovieListMapper;
import com.nisie.popularmovies.movielist.domain.mapper.MovieReviewMapper;
import com.nisie.popularmovies.movielist.domain.mapper.MovieTrailerMapper;
import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieReviewsDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerDomain;
import com.nisie.popularmovies.movielist.domain.network.service.MovieService;

import java.util.Map;

import rx.Observable;


/**
 * @author by natha on 6/26/2017.
 */

public class MovieListRepositoryImpl implements MovieListRepository {

    private final MovieService movieService;
    private final MovieListMapper movieListMapper;
    private final MovieTrailerMapper movieTrailerMapper;
    private final MovieReviewMapper movieReviewMapper;


    public MovieListRepositoryImpl(MovieService movieService,
                                   MovieListMapper movieListMapper,
                                   MovieTrailerMapper movieTrailerMapper,
                                   MovieReviewMapper movieReviewMapper
    ) {
        this.movieService = movieService;
        this.movieListMapper = movieListMapper;
        this.movieTrailerMapper = movieTrailerMapper;
        this.movieReviewMapper = movieReviewMapper;

    }

    @Override
    public Observable<MovieResultDomain> getMovies(Map<String, Object> params) {
        return movieService.getApi()
                .getMovieList(params)
                .map(movieListMapper);
    }

    @Override
    public Observable<MovieResultDomain> getMostPopularMovies(Map<String, Object> params) {
        return movieService.getApi()
                .getMostPopularMovie(params)
                .map(movieListMapper);
    }

    @Override
    public Observable<MovieResultDomain> getHighestRatedMovies(Map<String, Object> params) {
        return movieService.getApi()
                .getTopRatedMovie(params)
                .map(movieListMapper);
    }

    @Override
    public Observable<MovieTrailerDomain> getMovieTrailers(Map<String, Object> requestParams) {
        return movieService.getApi()
                .getMovieTrailer(
                        String.valueOf(requestParams.get(GetMovieTrailerUseCase.ID)),
                        requestParams)
                .map(movieTrailerMapper);
    }

    @Override
    public Observable<MovieReviewsDomain> getMovieReviews(Map<String, Object> requestParams) {
        return movieService.getApi()
                .getMovieReviews(
                        String.valueOf(requestParams.get(GetMovieTrailerUseCase.ID)),
                        requestParams)
                .map(movieReviewMapper);
    }
}
