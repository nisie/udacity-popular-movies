package com.nisie.popularmovies.movielist.presentation.presenter;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.SQLException;
import android.graphics.Movie;
import android.net.Uri;
import android.util.Log;

import com.nisie.popularmovies.movielist.data.MovieContract;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieReviewsUseCase;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieTrailerUseCase;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.subscriber.GetMovieReviewsSubscriber;
import com.nisie.popularmovies.movielist.presentation.subscriber.GetMovieTrailerSubscriber;

import static android.content.ContentValues.TAG;

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

    @Override
    public void addFavorite(ContentResolver contentResolver, MovieItem movieItem) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MovieContract.MovieEntry.COLUMN_ID, movieItem.getId());
        contentValues.put(MovieContract.MovieEntry.COLUMN_IMAGE, movieItem.getImgUrl());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RATING, movieItem.getRatingText());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, movieItem.getReleaseDate());
        contentValues.put(MovieContract.MovieEntry.COLUMN_SYNOPSIS, movieItem.getSynopsis());
        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, movieItem.getTitle());

        Uri uri = contentResolver.insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);
        if (uri != null) {
            Log.d(MovieDetailPresenterImpl.class.getSimpleName(), uri.toString());
        }
    }

    @Override
    public void removeFavorite(ContentResolver contentResolver, int id) {
        try {
            int delete = contentResolver.delete(MovieContract.MovieEntry.CONTENT_URI,
                    MovieContract.MovieEntry.COLUMN_ID + " =?",
                    new String[]{String.valueOf(id)});
            Log.d(MovieDetailPresenterImpl.class.getSimpleName(), delete + "");
        } catch (SQLException e) {
            Log.e(MovieDetailPresenterImpl.class.getSimpleName(), e.toString());
        }
    }


}
