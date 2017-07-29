package com.nisie.popularmovies.movielist.domain.repository;

import android.content.Context;
import android.database.Cursor;

import com.nisie.popularmovies.movielist.data.MovieContract;
import com.nisie.popularmovies.movielist.domain.interactor.GetFavoritedMoviesUseCase;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieTrailerUseCase;
import com.nisie.popularmovies.movielist.domain.mapper.MovieListMapper;
import com.nisie.popularmovies.movielist.domain.mapper.MovieReviewMapper;
import com.nisie.popularmovies.movielist.domain.mapper.MovieTrailerMapper;
import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieReviewsDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerDomain;
import com.nisie.popularmovies.movielist.domain.network.service.MovieService;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;

import java.util.ArrayList;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;


/**
 * @author by natha on 6/26/2017.
 */

public class MovieListRepositoryImpl implements MovieListRepository {

    private final MovieService movieService;
    private final MovieListMapper movieListMapper;
    private final MovieTrailerMapper movieTrailerMapper;
    private final MovieReviewMapper movieReviewMapper;
    private Context context;

    public MovieListRepositoryImpl(Context context,
                                   MovieService movieService,
                                   MovieListMapper movieListMapper,
                                   MovieTrailerMapper movieTrailerMapper,
                                   MovieReviewMapper movieReviewMapper
    ) {
        this.context = context;
        this.movieService = movieService;
        this.movieListMapper = movieListMapper;
        this.movieTrailerMapper = movieTrailerMapper;
        this.movieReviewMapper = movieReviewMapper;

    }

    @Override
    public Observable<MovieResultDomain> getMovies(Map<String, Object> params) {
        return movieService.getApi()
                .getMovieList(params)
                .map(movieListMapper);
    }

    @Override
    public Observable<MovieResultDomain> getMostPopularMovies(Map<String, Object> params) {
        return movieService.getApi()
                .getMostPopularMovie(params)
                .map(movieListMapper);
    }

    @Override
    public Observable<MovieResultDomain> getHighestRatedMovies(Map<String, Object> params) {
        return movieService.getApi()
                .getTopRatedMovie(params)
                .map(movieListMapper);
    }

    @Override
    public Observable<MovieTrailerDomain> getMovieTrailers(Map<String, Object> requestParams) {
        return movieService.getApi()
                .getMovieTrailer(
                        String.valueOf(requestParams.get(GetMovieTrailerUseCase.ID)),
                        requestParams)
                .map(movieTrailerMapper);
    }

    @Override
    public Observable<MovieReviewsDomain> getMovieReviews(Map<String, Object> requestParams) {
        return movieService.getApi()
                .getMovieReviews(
                        String.valueOf(requestParams.get(GetMovieTrailerUseCase.ID)),
                        requestParams)
                .map(movieReviewMapper);
    }

    @Override
    public Observable<ArrayList<MovieItem>> getFavoritedMovies(Map<String, Object> requestParams) {
        return Observable.just(getFavoriteMovieCursor(requestParams))
                .flatMap(new Func1<Cursor, Observable<ArrayList<MovieItem>>>() {
                    @Override
                    public Observable<ArrayList<MovieItem>> call(Cursor cursor) {
                        ArrayList<MovieItem> list = new ArrayList<>();
                        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                            list.add(
                                    new MovieItem(
                                            cursor.getInt(cursor.getColumnIndex(MovieContract.MovieEntry
                                                    .COLUMN_ID)),
                                            cursor.getString(cursor.getColumnIndex(MovieContract
                                                    .MovieEntry
                                                    .COLUMN_IMAGE)),
                                            cursor.getString(cursor.getColumnIndex(MovieContract
                                                    .MovieEntry
                                                    .COLUMN_TITLE)),
                                            cursor.getString(cursor.getColumnIndex(MovieContract
                                                    .MovieEntry
                                                    .COLUMN_RELEASE_DATE)),
                                            cursor.getFloat(cursor.getColumnIndex(MovieContract
                                                    .MovieEntry
                                                    .COLUMN_RATING)),
                                            cursor.getString(cursor.getColumnIndex(MovieContract
                                                    .MovieEntry
                                                    .COLUMN_SYNOPSIS))

                                    )
                            );
                        }
                        return Observable.just(list);
                    }
                });
    }

    private Cursor getFavoriteMovieCursor(Map<String, Object> requestParams) {
        if (requestParams.containsKey(GetFavoritedMoviesUseCase.ID)) {
            return context.getContentResolver().query(
                    MovieContract.MovieEntry.CONTENT_URI,
                    null,
                    MovieContract.MovieEntry.COLUMN_ID + "=?",
                    new String[]{String.valueOf(requestParams.get(GetFavoritedMoviesUseCase.ID))},
                    null);
        } else {
            return context.getContentResolver().query(
                    MovieContract.MovieEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
        }
    }
}
