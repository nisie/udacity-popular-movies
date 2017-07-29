package com.nisie.popularmovies.movielist.presentation.presenter;

import android.content.ContentResolver;

import com.nisie.popularmovies.movielist.domain.interactor.GetFavoritedMoviesUseCase;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieListUseCase;
import com.nisie.popularmovies.movielist.presentation.subscriber.GetFavoritedMoviesSubscriber;
import com.nisie.popularmovies.movielist.presentation.subscriber.GetMovieListSubscriber;

import java.io.File;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * @author by natha on 6/26/2017.
 */

public class MovieListPresenterImpl
        implements MovieListPresenter {

    private final MovieListPresenter.View viewListener;
    private final GetMovieListUseCase getMovieListUseCase;
    private final GetFavoritedMoviesUseCase getFavoritedMoviesUseCase;
    private int currentPage;

    public MovieListPresenterImpl(View viewListener,
                                  GetMovieListUseCase getMovieListUseCase,
                                  GetFavoritedMoviesUseCase getFavoritedMoviesUseCase) {
        this.viewListener = viewListener;
        this.getMovieListUseCase = getMovieListUseCase;
        this.currentPage = 1;
        this.getFavoritedMoviesUseCase = getFavoritedMoviesUseCase;

    }

    @Override
    public void unbind() {
        getMovieListUseCase.unsubscribe();
        getFavoritedMoviesUseCase.unsubscribe();
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

    @Override
    public void getFavorited(ContentResolver contentResolver) {
        viewListener.showLoading();
        getFavoritedMoviesUseCase.execute(GetFavoritedMoviesUseCase.makeParam(),
                new GetFavoritedMoviesSubscriber(viewListener));
    }

}
