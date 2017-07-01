package com.nisie.popularmovies.movielist.presentation.presenter;

import com.nisie.popularmovies.movielist.domain.interactor.GetMovieListUseCase;
import com.nisie.popularmovies.movielist.presentation.subscriber.GetMovieListSubscriber;

/**
 * @author by natha on 6/26/2017.
 */

public class MovieListPresenterImpl
        implements MovieListPresenter {

    private final MovieListPresenter.View viewListener;
    private final GetMovieListUseCase getMovieListUseCase;
    private int currentPage;

    public MovieListPresenterImpl(MovieListPresenter.View viewListener,
                                  GetMovieListUseCase getMovieListUseCase) {
        this.viewListener = viewListener;
        this.getMovieListUseCase = getMovieListUseCase;
        this.currentPage = 1;

    }

    @Override
    public void getMovieList() {
        viewListener.showLoading();
        getMovieListUseCase.execute(GetMovieListUseCase.makeParam(currentPage),
                new GetMovieListSubscriber(viewListener));
    }

    @Override
    public void getHighestRated() {
        viewListener.showLoading();
        getMovieListUseCase.execute(GetMovieListUseCase.makeParamHighestRated(currentPage),
                new GetMovieListSubscriber(viewListener));
    }

    @Override
    public void getMostPopular() {
        viewListener.showLoading();
        getMovieListUseCase.execute(GetMovieListUseCase.makeParamMostPopular(currentPage),
                new GetMovieListSubscriber(viewListener));
    }

    @Override
    public void loadMore(int lastItemPosition, int visibleItem) {
        currentPage += 1;
        getMovieList();
    }
}
