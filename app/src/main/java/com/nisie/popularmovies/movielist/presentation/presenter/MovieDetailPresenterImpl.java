package com.nisie.popularmovies.movielist.presentation.presenter;

import com.nisie.popularmovies.movielist.domain.interactor.GetMovieReviewsUseCase;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieTrailerUseCase;
import com.nisie.popularmovies.movielist.presentation.subscriber.GetMovieReviewsSubscriber;
import com.nisie.popularmovies.movielist.presentation.subscriber.GetMovieTrailerSubscriber;
import com.nisie.popularmovies.movielist.presentation.ui.activity.MovieDetailActivity;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter {


    private final View viewListener;
    private final GetMovieTrailerUseCase getMovieTrailerUseCase;
    private final GetMovieReviewsUseCase getMovieReviewsUseCase;

    public MovieDetailPresenterImpl(MovieDetailPresenter.View viewListener,
                                    GetMovieTrailerUseCase getMovieTrailerUseCase,
                                    GetMovieReviewsUseCase getMovieReviewsUseCase) {

        this.viewListener = viewListener;
        this.getMovieTrailerUseCase = getMovieTrailerUseCase;
        this.getMovieReviewsUseCase = getMovieReviewsUseCase;

    }

    @Override
    public void unbind() {
        getMovieReviewsUseCase.unsubscribe();
        getMovieTrailerUseCase.unsubscribe();
    }

    @Override
    public void getTrailers(int id) {
        viewListener.showLoadingTrailers();
        getMovieTrailerUseCase.execute(GetMovieTrailerUseCase.makeParam(id),
                new GetMovieTrailerSubscriber(viewListener));
    }

    @Override
    public void getReviews(int id) {
        viewListener.showLoadingReviews();
        getMovieReviewsUseCase.execute(GetMovieTrailerUseCase.makeParam(id),
                new GetMovieReviewsSubscriber(viewListener));
    }


}
