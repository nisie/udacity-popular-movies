package com.nisie.popularmovies.movielist.domain.network.api;

import com.nisie.popularmovies.movielist.domain.interactor.GetMovieReviewsUseCase;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieTrailerUseCase;
import com.nisie.popularmovies.movielist.domain.model.MovieReviewsDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerDomain;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieItemResult;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieReviewResult;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieTrailerResult;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author by natha on 6/24/2017.
 */

public interface MovieAPI {

    @GET("discover/movie")
    Observable<Response<MovieItemResult>> getMovieList(@QueryMap Map<String, Object> params);

    @GET("movie/popular")
    Observable<Response<MovieItemResult>> getMostPopularMovie(@QueryMap Map<String, Object> params);

    @GET("movie/top_rated")
    Observable<Response<MovieItemResult>> getTopRatedMovie(@QueryMap Map<String, Object> params);

    @GET("movie/{id}/videos")
    Observable<Response<MovieTrailerResult>> getMovieTrailer(@Path(GetMovieTrailerUseCase.ID) String
                                                                  id,
                                                             @QueryMap Map<String, Object> requestParams);

    @GET("movie/{id}/reviews")
    Observable<Response<MovieReviewResult>> getMovieReviews(@Path(GetMovieReviewsUseCase.ID) String id,
                                                            @QueryMap Map<String, Object> requestParams);
}
