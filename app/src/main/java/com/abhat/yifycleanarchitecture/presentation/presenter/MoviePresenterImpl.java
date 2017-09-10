package com.abhat.yifycleanarchitecture.presentation.presenter;

import android.util.Log;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.domain.usecases.UseCase;
import com.abhat.yifycleanarchitecture.presentation.view.MovieListView;

import rx.Subscriber;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class MoviePresenterImpl implements MoviePresenter {

    private MovieListView mMovieListView;
    private UseCase mGetMovieListUseCase;

    public MoviePresenterImpl(MovieListView movieListView, UseCase getMovieListUseCase) {
        this.mMovieListView = movieListView;
        this.mGetMovieListUseCase = getMovieListUseCase;
    }

    @Override
    public void getMovieList(String limit, String searchQuery) {
        mGetMovieListUseCase.execute(limit, searchQuery)
                .subscribe(new Subscriber<ApiResponseData>() {
                    @Override
                    public void onCompleted() {
                        //Log.d("YIFY", "ON COMPLETE CALLED");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.d("YIFY", "ON ERROR CALLED");
                    }

                    @Override
                    public void onNext(ApiResponseData data) {
                        //Log.d("YIFY", "ON NEXT CALLED");
                        if (mMovieListView != null) {
                            mMovieListView.displayMovieList(data.getData().getMovies());
                        }
                    }
                });
    }
}