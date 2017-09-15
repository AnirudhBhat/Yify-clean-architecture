package com.abhat.yifycleanarchitecture.presentation.presenter;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Data;
import com.abhat.yifycleanarchitecture.data.model.Movie;
import com.abhat.yifycleanarchitecture.domain.usecases.GetMovieDetailUseCase;
import com.abhat.yifycleanarchitecture.domain.usecases.UseCase;
import com.abhat.yifycleanarchitecture.presentation.view.MovieDetailView;

import rx.Subscriber;

/**
 * Created by Anirudh Uppunda on 15/9/17.
 */

public class MoviePresenterImpl implements MoviePresenter {

    private MovieDetailView mMovieDetailView;
    private GetMovieDetailUseCase mGetMovieDetailUseCase;

    public MoviePresenterImpl(MovieDetailView view, GetMovieDetailUseCase getMovieDetailUseCase) {
        this.mMovieDetailView = view;
        this.mGetMovieDetailUseCase = getMovieDetailUseCase;
    }


    @Override
    public void getMovieDetail(String id) {
        mGetMovieDetailUseCase.execute(id, "true", "")
                .subscribe(new Subscriber<ApiResponseData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mMovieDetailView != null) {
                            mMovieDetailView.displayError();
                        }
                    }

                    @Override
                    public void onNext(ApiResponseData data) {
                        if (mMovieDetailView != null) {
                            mMovieDetailView.displayMovieDetails(data.getData().getMovie());
                        }
                    }
                });
    }
}
