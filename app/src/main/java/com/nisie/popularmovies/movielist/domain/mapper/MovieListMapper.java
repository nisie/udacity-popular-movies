package com.nisie.popularmovies.movielist.domain.mapper;

import com.nisie.popularmovies.main.network.BaseMapper;
import com.nisie.popularmovies.main.network.ErrorNetworkException;
import com.nisie.popularmovies.movielist.domain.model.MovieItemDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieResultDomain;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieItemResult;
import com.nisie.popularmovies.movielist.domain.network.pojo.Result;

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
                convertToListDomainItem(result.getResults()));
    }

    private List<MovieItemDomain> convertToListDomainItem(List<Result> results) {
        List<MovieItemDomain> listDomain = new ArrayList<>();
        if (results != null) {
            for (Result result : results) {
                listDomain.add(new MovieItemDomain(result.getVoteCount(),
                        result.getId(),
                        result.isVideo(),
                        result.getVoteAverage(),
                        result.getTitle(),
                        result.getPopularity(),
                        result.getPosterPath(),
                        result.getOriginalLanguage(),
                        result.getOriginalTitle(),
                        result.getGenreIds(),
                        result.getBackdropPath(),
                        result.isAdult(),
                        result.getOverview(),
                        result.getReleaseDate()
                ));
            }
        }
        return listDomain;
    }
}
