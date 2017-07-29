package com.nisie.popularmovies.movielist.domain.mapper;

import com.nisie.popularmovies.main.network.BaseMapper;
import com.nisie.popularmovies.main.network.ErrorNetworkException;
import com.nisie.popularmovies.movielist.domain.model.MovieItemDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieItemResult;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieItemPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.functions.Func1;

/**
 * @author by natha on 6/26/2017.
 */

public class MovieListMapper extends BaseMapper implements Func1<Response<MovieItemResult>, MovieResultDomain> {

    @Override
    public MovieResultDomain call(Response<MovieItemResult> resultResponse) {
        MovieResultDomain domain;
        if (resultResponse.isSuccessful())
            domain = convertToDomain(resultResponse.body());
        else
            throw new ErrorNetworkException(String.valueOf(resultResponse.code()));

        return domain;

    }

    private MovieResultDomain convertToDomain(MovieItemResult result) {

        return new MovieResultDomain(result.getPage(),
                result.getTotalResults(),
                result.getTotalPages(),
                convertToListDomainItem(result.getMovieItemPojos()));
    }

    private List<MovieItemDomain> convertToListDomainItem(List<MovieItemPojo> results) {
        List<MovieItemDomain> listDomain = new ArrayList<>();
        if (results != null) {
            for (MovieItemPojo movieItemPojo : results) {
                listDomain.add(new MovieItemDomain(movieItemPojo.getVoteCount(),
                        movieItemPojo.getId(),
                        movieItemPojo.isVideo(),
                        movieItemPojo.getVoteAverage(),
                        movieItemPojo.getTitle(),
                        movieItemPojo.getPopularity(),
                        movieItemPojo.getPosterPath(),
                        movieItemPojo.getOriginalLanguage(),
                        movieItemPojo.getOriginalTitle(),
                        movieItemPojo.getGenreIds(),
                        movieItemPojo.getBackdropPath(),
                        movieItemPojo.isAdult(),
                        movieItemPojo.getOverview(),
                        movieItemPojo.getReleaseDate()
                ));
            }
        }
        return listDomain;
    }
}
