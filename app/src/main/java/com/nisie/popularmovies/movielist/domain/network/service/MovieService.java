package com.nisie.popularmovies.movielist.domain.network.service;

import com.nisie.popularmovies.main.network.BaseService;
import com.nisie.popularmovies.movielist.domain.network.api.MovieAPI;

import retrofit2.Retrofit;

/**
 * @author by natha on 6/26/2017.
 */

public class MovieService extends BaseService<MovieAPI> {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    protected void initApiService(Retrofit retrofit) {
        api = retrofit.create(MovieAPI.class);
    }

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    public MovieAPI getApi() {
        return api;
    }
}
