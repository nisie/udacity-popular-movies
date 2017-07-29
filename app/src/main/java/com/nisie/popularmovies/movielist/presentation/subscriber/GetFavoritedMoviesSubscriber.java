package com.nisie.popularmovies.movielist.presentation.subscriber;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieListPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * @author by nisie on 7/30/17.
 */

public class GetFavoritedMoviesSubscriber extends Subscriber<ArrayList<MovieItem>> {

    private final MovieListPresenter.View viewListener;

    public GetFavoritedMoviesSubscriber(MovieListPresenter.View viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        viewListener.onErrorGetMovieList(R.string.default_error);

    }

    @Override
    public void onNext(ArrayList<MovieItem> movieItems) {
        viewListener.onSuccessGetMovieList(movieItems);
    }
}
