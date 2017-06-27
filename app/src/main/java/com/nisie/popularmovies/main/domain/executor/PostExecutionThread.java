package com.nisie.popularmovies.main.domain.executor;

import rx.Scheduler;

/**
 * @author by natha on 6/27/2017.
 */

public interface PostExecutionThread {

    Scheduler getScheduler();
}
