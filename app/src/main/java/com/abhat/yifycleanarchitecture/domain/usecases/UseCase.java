package com.abhat.yifycleanarchitecture.domain.usecases;

import rx.Observable;

/**
 * Created by cumulations on 5/9/17.
 */

public abstract class UseCase<T> {

    public abstract Observable<T> buildUseCase(String sortBy, String quality);

    public Observable<T> execute(String sortBy, String quality) {
        return buildUseCase(sortBy, quality);
    }
}
