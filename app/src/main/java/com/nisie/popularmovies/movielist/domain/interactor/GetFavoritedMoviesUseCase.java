package com.nisie.popularmovies.movielist.domain.interactor;

import com.nisie.popularmovies.main.domain.executor.PostExecutionThread;
import com.nisie.popularmovies.main.domain.executor.ThreadExecutor;
import com.nisie.popularmovies.main.presentation.UseCase;
import com.nisie.popularmovies.movielist.domain.repository.MovieListRepository;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * @author by nisie on 7/30/17.
 */

public class GetFavoritedMoviesUseCase extends UseCase<ArrayList<MovieItem>> {
    private final MovieListRepository repository;

    public GetFavoritedMoviesUseCase(ThreadExecutor threadExecutor,
                                     PostExecutionThread postExecutionThread,
                                     MovieListRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;

    }

    @Override
    public Observable<ArrayList<MovieItem>> createObservable(Map<String, Object> requestParams) {
        return repository.getFavoritedMovies();
    }

    public static Map<String, Object> makeParam() {
        return new HashMap<>();
    }
}
