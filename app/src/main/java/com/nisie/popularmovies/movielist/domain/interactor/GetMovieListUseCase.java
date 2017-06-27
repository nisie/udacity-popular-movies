package com.nisie.popularmovies.movielist.domain.interactor;

import com.nisie.popularmovies.main.presentation.UseCase;
import com.nisie.popularmovies.main.domain.executor.PostExecutionThread;
import com.nisie.popularmovies.main.domain.executor.ThreadExecutor;
import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.domain.repository.MovieListRepository;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * @author by natha on 6/27/2017.
 */

public class GetMovieListUseCase extends UseCase<MovieResultDomain> {

    private static final String API_KEY = "api_key";
    private static final String KEY = "e355e388ec3a7934853ad2a2557f2b05";

    private MovieListRepository repository;

    public GetMovieListUseCase(ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread,
                               MovieListRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Observable<MovieResultDomain> createObservable(Map<String, Object> requestParams) {
        return repository.getMovies(requestParams);
    }

    public static Map<String, Object> makeParam() {
        Map<String, Object> param = new HashMap<>();
        param.put(API_KEY, KEY);
        return param;
    }
}
