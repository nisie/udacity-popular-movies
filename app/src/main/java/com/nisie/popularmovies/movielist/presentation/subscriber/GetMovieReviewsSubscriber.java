package com.nisie.popularmovies.movielist.presentation.subscriber;

import android.databinding.ObservableArrayList;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.movielist.domain.model.MovieReviewItemDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieReviewsDomain;
import com.nisie.popularmovies.movielist.presentation.model.MovieReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.model.ReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static android.R.transition.move;

/**
 * @author by nisie on 7/29/17.
 */

public class GetMovieReviewsSubscriber extends Subscriber<MovieReviewsDomain> {

    private final MovieDetailPresenter.View viewListener;

    public GetMovieReviewsSubscriber(MovieDetailPresenter.View viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        viewListener.finishLoadingReviews();
        viewListener.onErrorGetReviews(R.string.default_error);
    }

    @Override
    public void onNext(MovieReviewsDomain movieReviewsDomain) {
        viewListener.finishLoadingReviews();
        viewListener.onSuccessGetReviews(convertToViewModel(movieReviewsDomain));

    }

    private ReviewViewModel convertToViewModel(
            MovieReviewsDomain movieReviewsDomain) {
        return new ReviewViewModel(convertToList(movieReviewsDomain));

    }

    private ObservableArrayList<MovieReviewViewModel> convertToList(MovieReviewsDomain movieReviewsDomain) {
        ObservableArrayList<MovieReviewViewModel> listReview = new ObservableArrayList<>();
        for (MovieReviewItemDomain domain : movieReviewsDomain.getResults()) {
            listReview.add(new MovieReviewViewModel(
                    domain.getAuthor(),
                    domain.getContent()
            ));
        }
        return listReview;
    }
}
