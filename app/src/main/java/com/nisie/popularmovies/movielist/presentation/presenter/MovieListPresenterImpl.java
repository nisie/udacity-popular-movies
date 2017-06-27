package com.nisie.popularmovies.movielist.presentation.presenter;

import com.nisie.popularmovies.movielist.domain.interactor.GetMovieListUseCase;
import com.nisie.popularmovies.movielist.presentation.subscriber.GetMovieListSubscriber;

/**
 * @author by natha on 6/26/2017.
 */

public class MovieListPresenterImpl
        implements MovieListPresenter{

    private final MovieListPresenter.View viewListener;
    private final GetMovieListUseCase getMovieListUseCase;

    public MovieListPresenterImpl(MovieListPresenter.View viewListener,
                                  GetMovieListUseCase getMovieListUseCase) {
        this.viewListener = viewListener;
        this.getMovieListUseCase = getMovieListUseCase;
    }

    @Override
    public void getMovieList() {
        getMovieListUseCase.execute(GetMovieListUseCase.makeParam(),
                new GetMovieListSubscriber(viewListener));
    }
}
