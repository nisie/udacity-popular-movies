package com.nisie.popularmovies.movielist.domain.mapper;

import com.nisie.popularmovies.main.network.BaseMapper;
import com.nisie.popularmovies.main.network.ErrorNetworkException;
import com.nisie.popularmovies.movielist.domain.model.MovieReviewItemDomain;
import com.nisie.popularmovies.movielist.domain.model.MovieReviewsDomain;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieReviewItemPojo;
import com.nisie.popularmovies.movielist.domain.network.pojo.MovieReviewResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.functions.Func1;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieReviewMapper extends BaseMapper implements Func1<Response<MovieReviewResult>,
        MovieReviewsDomain> {
    @Override
    public MovieReviewsDomain call(Response<MovieReviewResult> resultResponse) {
        MovieReviewsDomain domain;
        if (resultResponse.isSuccessful())
            domain = convertToDomain(resultResponse.body());
        else
            throw new ErrorNetworkException(String.valueOf(resultResponse.code()));

        return domain;
    }

    private MovieReviewsDomain convertToDomain(MovieReviewResult result) {
        return new MovieReviewsDomain(
                result.getId(),
                result.getPage(),
                convertToReviews(result.getResults()),
                result.getTotalPages(),
                result.getTotalResults()

        );
    }

    private List<MovieReviewItemDomain> convertToReviews(List<MovieReviewItemPojo> results) {
        List<MovieReviewItemDomain> listDomain = new ArrayList<>();
        for (MovieReviewItemPojo pojo : results) {
            listDomain.add(new MovieReviewItemDomain(
                    pojo.getId(),
                    pojo.getAuthor(),
                    pojo.getContent(),
                    pojo.getUrl()
            ));
        }
        return listDomain;
    }

}
