package com.nisie.popularmovies.main.presentation;

import com.nisie.popularmovies.main.domain.executor.PostExecutionThread;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author by natha on 6/27/2017.
 */

public class UIThread implements PostExecutionThread {

    public UIThread(){}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
