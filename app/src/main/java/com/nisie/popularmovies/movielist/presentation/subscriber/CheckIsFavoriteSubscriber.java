package com.nisie.popularmovies.movielist.presentation.subscriber;

import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieDetailPresenter;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * @author by nisie on 7/30/17.
 */

public class CheckIsFavoriteSubscriber extends Subscriber<ArrayList<MovieItem>> {
    private final MovieDetailPresenter.View viewListener;

    public CheckIsFavoriteSubscriber(MovieDetailPresenter.View viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(ArrayList<MovieItem> movieItems) {
        if(!movieItems.isEmpty())
            viewListener.onIsFavorited();
    }
}
