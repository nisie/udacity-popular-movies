package com.nisie.popularmovies.main.network;

/**
 * @author by natha on 6/27/2017.
 */

public class ErrorNetworkException extends RuntimeException {
    public ErrorNetworkException(String errorMessage) {
        super(errorMessage);
    }
}
