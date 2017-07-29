package com.nisie.popularmovies.movielist.domain.interactor;

import com.nisie.popularmovies.main.presentation.UseCase;
import com.nisie.popularmovies.main.domain.executor.PostExecutionThread;
import com.nisie.popularmovies.main.domain.executor.ThreadExecutor;
import com.nisie.popularmovies.movielist.domain.APICONSTANT;
import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.domain.repository.MovieListRepository;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * @author by natha on 6/27/2017.
 */

public class GetMovieListUseCase extends UseCase<MovieResultDomain> implements APICONSTANT {

    private static final String SORT_BY = "sort_by";
    private static final Object HIGHEST_RATED = "vote_average.desc";
    private static final Object MOST_POPULAR = "popularity.desc";
    private static final String PAGE = "page";

    private MovieListRepository repository;

    public GetMovieListUseCase(ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread,
                               MovieListRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Observable<MovieResultDomain> createObservable(Map<String, Object> requestParams) {
        if (requestParams.containsValue(HIGHEST_RATED)) {
            return repository.getHighestRatedMovies(requestParams);
        } else if (requestParams.containsValue(MOST_POPULAR)) {
            return repository.getMostPopularMovies(requestParams);
        } else
            return repository.getMovies(requestParams);
    }

    public static Map<String, Object> makeParam(int currentPage) {
        Map<String, Object> param = new HashMap<>();
        param.put(API_KEY, KEY);
        param.put(PAGE, currentPage);
        return param;
    }

    public static Map<String, Object> makeParamHighestRated(int currentPage) {
        Map<String, Object> param = makeParam(currentPage);
        param.put(SORT_BY, HIGHEST_RATED);
        return param;
    }

    public static Map<String, Object> makeParamMostPopular(int currentPage) {
        Map<String, Object> param = makeParam(currentPage);
        param.put(SORT_BY, MOST_POPULAR);
        return param;
    }
}
