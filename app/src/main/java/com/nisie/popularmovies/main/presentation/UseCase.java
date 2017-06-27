package com.nisie.popularmovies.main.presentation;

import com.nisie.popularmovies.main.domain.executor.PostExecutionThread;
import com.nisie.popularmovies.main.domain.executor.ThreadExecutor;
import com.nisie.popularmovies.main.domain.interactor.Interactor;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author by natha on 6/27/2017.
 */

public abstract class UseCase<T> implements Interactor<T> {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    protected Subscription subscription = Subscriptions.empty();

    public UseCase(ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public abstract Observable<T> createObservable(Map<String, Object> requestParams);

    public void execute(Map<String, Object> requestParams, Subscriber<T> subscriber) {
        this.subscription = createObservable(requestParams)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (!this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
    }
}
