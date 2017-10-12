package com.abhat.yifycleanarchitecture;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Movie;
import com.abhat.yifycleanarchitecture.domain.usecases.UseCase;
import com.abhat.yifycleanarchitecture.presentation.presenter.MoviePresenter;
import com.abhat.yifycleanarchitecture.presentation.presenter.MoviePresenterImpl;
import com.abhat.yifycleanarchitecture.presentation.view.MovieDetailView;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Anirudh Uppunda on 10/10/17.
 */

public class MoviePresenterImplTest {
    private MockView view;

    @Test
    public void shouldPassMovieDetailToView() {
        // given
        view = new MockView();
        UseCase<ApiResponseData> useCase = new MockUseCase();

        // when
        MoviePresenter moviePresenter = new MoviePresenterImpl(view, useCase);
        moviePresenter.getMovieDetail("");

        // then
        Assert.assertEquals(true, ((MockView)view).passed);

    }
    private class MockView implements MovieDetailView {
        boolean passed;

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void displayError() {

        }

        @Override
        public void displayMovieDetails(Movie movie) {
            passed = true;
        }
    }

    public class MockUseCase extends UseCase<ApiResponseData> {

        @Override
        public Observable<ApiResponseData> buildUseCase() {
            Observable<ApiResponseData> observable = Observable.fromCallable(new Callable<ApiResponseData>() {
                @Override
                public ApiResponseData call() throws Exception {
                    return new ApiResponseData();
                }
            });
            observable.subscribe(new Subscriber<ApiResponseData>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ApiResponseData apiResponseData) {
                    view.displayMovieDetails(new Movie());
                }
            });
            return observable;
        }
    }
}
