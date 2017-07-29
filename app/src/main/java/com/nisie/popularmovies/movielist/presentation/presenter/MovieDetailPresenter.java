package com.nisie.popularmovies.movielist.presentation.presenter;

import com.nisie.popularmovies.main.presentation.BasePresenter;
import com.nisie.popularmovies.movielist.presentation.model.MovieReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.model.MovieTrailerViewModel;
import com.nisie.popularmovies.movielist.presentation.model.ReviewViewModel;

import java.util.ArrayList;

/**
 * @author by nisie on 7/29/17.
 */

public interface MovieDetailPresenter extends BasePresenter{

    void getTrailers(int id);

    void getReviews(int id);


    interface View {

        void showLoadingTrailers();

        void onErrorGetTrailer(int resId);

        void onSuccessGetTrailer(ArrayList<MovieTrailerViewModel> movieTrailerViewModels);

        void finishLoadingTrailer();

        void showLoadingReviews();

        void onErrorGetReviews(int resId);

        void onSuccessGetReviews(ReviewViewModel movieReviewViewModels);

        void finishLoadingReviews();

    }

}
