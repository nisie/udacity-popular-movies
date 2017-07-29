package com.nisie.popularmovies.movielist.presentation.subscriber;

import android.util.Log;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.movielist.domain.model.MovieItemDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieListPresenter;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * @author by natha on 6/27/2017.
 */

public class GetMovieListSubscriber extends Subscriber<MovieResultDomain> {
    private final MovieListPresenter.View viewListener;

    public GetMovieListSubscriber(MovieListPresenter.View viewListener) {
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
    public void onNext(MovieResultDomain movieResultDomain) {
        if (movieResultDomain.getResults() != null && !movieResultDomain.getResults().isEmpty())
            viewListener.onSuccessGetMovieList(convertToViewModel(movieResultDomain));
        else
            viewListener.onErrorGetMovieList(R.string.default_error);


    }

    private ArrayList<MovieItem> convertToViewModel(MovieResultDomain movieResultDomain) {
        ArrayList<MovieItem> list = new ArrayList<>();
        for (MovieItemDomain domain : movieResultDomain.getResults()) {
            list.add(new MovieItem(
                    domain.getId(),
                    convertToImageUrl(domain.getPosterPath()),
                    domain.getTitle(),
                    domain.getReleaseDate(),
                    (float) domain.getVoteAverage(),
                    domain.getOverview()));
        }
        return list;
    }

    private String convertToImageUrl(String posterPath) {
        return "https://image.tmdb.org/t/p/w640" + posterPath;
    }
}
