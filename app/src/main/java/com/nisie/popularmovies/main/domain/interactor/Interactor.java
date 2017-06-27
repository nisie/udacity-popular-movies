package com.nisie.popularmovies.main.domain.interactor;

import java.util.Map;
import java.util.Objects;

import rx.Subscriber;

/**
 * @author by natha on 6/26/2017.
 */

public interface Interactor<T> {

    void execute(Map<String, Object> requestParams, Subscriber<T> subscriber);


}
