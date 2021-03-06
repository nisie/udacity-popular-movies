package com.nisie.popularmovies.movielist.presentation.presenter;

import android.content.ContentResolver;
import android.databinding.ObservableArrayList;

import com.nisie.popularmovies.main.presentation.BasePresenter;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.model.MovieReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.model.MovieTrailerViewModel;

/**
 * @author by nisie on 7/29/17.
 */

public interface MovieDetailPresenter extends BasePresenter{

    void getTrailers(int id);

    void getReviews(int id);

    void addFavorite(ContentResolver contentResolver, MovieItem movieItem);

    void removeFavorite(ContentResolver contentResolver, int id);

    void checkIsFavorite(int id);


    interface View {

        void showLoadingTrailers();

        void onErrorGetTrailer(int resId);

        void onSuccessGetTrailer(ObservableArrayList<MovieTrailerViewModel> movieTrailerViewModels);

        void finishLoadingTrailer();

        void showLoadingReviews();

        void onErrorGetReviews(int resId);

        void onSuccessGetReviews(ObservableArrayList<MovieReviewViewModel> movieReviewViewModels);

        void finishLoadingReviews();

        void onIsFavorited();

        void setFavoriteResultOk();

        void onEmptyReview();

        void onEmptyTrailer();
    }

}
