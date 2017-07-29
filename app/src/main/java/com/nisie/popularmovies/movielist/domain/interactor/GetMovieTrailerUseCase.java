package com.nisie.popularmovies.movielist.domain.interactor;

import com.nisie.popularmovies.main.domain.executor.PostExecutionThread;
import com.nisie.popularmovies.main.domain.executor.ThreadExecutor;
import com.nisie.popularmovies.main.presentation.UseCase;
import com.nisie.popularmovies.movielist.domain.APICONSTANT;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerDomain;
import com.nisie.popularmovies.movielist.domain.repository.MovieListRepository;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * @author by nisie on 7/29/17.
 */

public class GetMovieTrailerUseCase extends UseCase<MovieTrailerDomain> implements APICONSTANT {

    public static final String ID = "id";

    private MovieListRepository repository;

    public GetMovieTrailerUseCase(ThreadExecutor threadExecutor,
                                  PostExecutionThread postExecutionThread,
                                  MovieListRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Observable<MovieTrailerDomain> createObservable(Map<String, Object> requestParams) {
        return repository.getMovieTrailers(requestParams);
    }

    public static Map<String, Object> makeParam(int id) {
        Map<String, Object> param = new HashMap<>();
        param.put(API_KEY, KEY);
        param.put(ID, id);
        return param;
    }
}
