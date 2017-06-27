package com.nisie.popularmovies.movielist.presentation.presenter;

import com.nisie.popularmovies.main.presentation.BasePresenter;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;

import java.util.ArrayList;

/**
 * @author by natha on 6/26/2017.
 */

public interface MovieListPresenter extends BasePresenter{

    void getMovieList();

    interface View {

        void goToDetail(MovieItem movieItem);

        void onSuccessGetMovieList(ArrayList<MovieItem> aVoid);

        void onErrorGetMovieList(int resId);
    }

}
