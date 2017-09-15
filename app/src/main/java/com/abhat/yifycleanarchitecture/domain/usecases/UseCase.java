package com.abhat.yifycleanarchitecture.domain.usecases;

import com.abhat.yifycleanarchitecture.data.model.Data;

import rx.Observable;

/**
 * Created by cumulations on 5/9/17.
 */

public abstract class UseCase<T> {

    public abstract Observable<T> buildUseCase(String sortBy, String quality);
    public abstract Observable<T> buildMovieDetailUseCase(String id, String sortBy, String quality);

    public Observable<T> execute(String sortBy, String quality) {
        return buildUseCase(sortBy, quality);
    }

    public Observable<T> execute(String id, String withImages, String withCast) {
        return buildMovieDetailUseCase(id, withImages, withCast);
    }
}
