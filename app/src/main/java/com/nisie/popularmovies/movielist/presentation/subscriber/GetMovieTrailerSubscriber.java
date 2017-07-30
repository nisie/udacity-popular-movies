package com.nisie.popularmovies.movielist.presentation.subscriber;

import android.databinding.ObservableArrayList;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerItemDomain;
import com.nisie.popularmovies.movielist.presentation.model.MovieReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.model.MovieTrailerViewModel;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieDetailPresenter;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * @author by nisie on 7/29/17.
 */

public class GetMovieTrailerSubscriber extends Subscriber<MovieTrailerDomain> {
    private static final String YOUTUBE_VIDEO = "https://www.youtube.com/watch?v=";
    private static final String YOUTUBE_IMAGE = "http://img.youtube.com/vi/";
    private static final String DEFAULT_IMAGE = "/mqdefault.jpg";
    private final MovieDetailPresenter.View viewListener;

    public GetMovieTrailerSubscriber(MovieDetailPresenter.View viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        viewListener.finishLoadingTrailer();
        viewListener.onErrorGetTrailer(R.string.default_error);
    }

    @Override
    public void onNext(MovieTrailerDomain movieTrailerDomain) {
        viewListener.finishLoadingTrailer();
        ObservableArrayList<MovieTrailerViewModel> listModel = convertToViewModel
                (movieTrailerDomain);
        if (listModel.isEmpty()) {
            viewListener.onEmptyTrailer();
        } else {
            viewListener.onSuccessGetTrailer(listModel);
        }
    }

    private ObservableArrayList<MovieTrailerViewModel> convertToViewModel(MovieTrailerDomain movieTrailerDomain) {
        ObservableArrayList<MovieTrailerViewModel> listModel = new ObservableArrayList<>();
        for (MovieTrailerItemDomain domain : movieTrailerDomain.getListTrailer()) {
            listModel.add(new MovieTrailerViewModel(
                    convertToVideoUrl(domain.getKey()),
                    convertToThumbnailUrl(domain.getKey()),
                    domain.getName(),
                    domain.getKey()
            ));
        }
        return listModel;
    }

    private String convertToThumbnailUrl(String key) {
        return YOUTUBE_IMAGE + key + DEFAULT_IMAGE;
    }

    private String convertToVideoUrl(String key) {
        return YOUTUBE_VIDEO + key;
    }

}
