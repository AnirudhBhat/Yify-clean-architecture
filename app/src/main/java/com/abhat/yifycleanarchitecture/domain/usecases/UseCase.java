package com.abhat.yifycleanarchitecture.domain.usecases;

import com.abhat.yifycleanarchitecture.data.model.Data;

import rx.Observable;

/**
 * Created by cumulations on 5/9/17.
 */

public abstract class UseCase<T> {

    public abstract Observable<T> buildUseCase();
    //public abstract Observable<T> buildUseCase(String sortBy, String quality);
    //public abstract Observable<T> buildMovieDetailUseCase(String id, String sortBy, String quality);

    // Movie details params
    protected String id;
    protected String withImages;
    protected String withCast;

    // Movie list Params
    protected String limit;
    protected String searchQuery;

    public Observable<T> execute() {
        return buildUseCase();
    }

    public void setMovieDetailParams(String id, String withImages, String withCast) {
        this.id = id;
        this.withImages = withImages;
        this.withCast = withCast;
    }

    public void setMovieListParams(String limit, String searchQuery) {
        this.limit = limit;
        this.searchQuery = searchQuery;
    }

    /*public Observable<T> execute(String id, String withImages, String withCast) {
        return buildMovieDetailUseCase(id, withImages, withCast);
    }*/
}
