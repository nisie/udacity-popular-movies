package com.nisie.popularmovies.movielist.domain.mapper;

import com.nisie.popularmovies.main.network.BaseMapper;
import com.nisie.popularmovies.main.network.ErrorNetworkException;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieTrailerItemDomain;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieTrailerPojo;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieTrailerResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.functions.Func1;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieTrailerMapper extends BaseMapper implements Func1<Response<MovieTrailerResult>,
        MovieTrailerDomain> {
    @Override
    public MovieTrailerDomain call(Response<MovieTrailerResult> resultResponse) {
        MovieTrailerDomain domain;
        if (resultResponse.isSuccessful())
            domain = convertToDomain(resultResponse.body());
        else
            throw new ErrorNetworkException(String.valueOf(resultResponse.code()));

        return domain;
    }

    private MovieTrailerDomain convertToDomain(MovieTrailerResult result) {
        return new MovieTrailerDomain(result.getId(),
                convertToListTrailers(result.getMovieTrailerPojos()));
    }

    private List<MovieTrailerItemDomain> convertToListTrailers(List<MovieTrailerPojo> movieTrailerPojos) {
        List<MovieTrailerItemDomain> listDomain = new ArrayList<>();
        for (MovieTrailerPojo pojo : movieTrailerPojos) {
            listDomain.add(new MovieTrailerItemDomain(
                    pojo.getId(),
                    pojo.getIso6391(),
                    pojo.getIso31661(),
                    pojo.getKey(),
                    pojo.getName(),
                    pojo.getSite(),
                    pojo.getSize(),
                    pojo.getType()
            ));
        }
        return listDomain;
    }
}
