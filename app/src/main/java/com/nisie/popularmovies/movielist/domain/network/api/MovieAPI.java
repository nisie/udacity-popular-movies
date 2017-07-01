package com.nisie.popularmovies.movielist.domain.network.api;

import com.nisie.popularmovies.movielist.domain.network.pojo.MovieItemResult;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.GET;
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
}
