package com.abhat.yifycleanarchitecture;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Movie;
import com.abhat.yifycleanarchitecture.data.repository.MovieRepository;
import com.abhat.yifycleanarchitecture.domain.UseCase;
import com.abhat.yifycleanarchitecture.presentation.presenter.MoviePresenterImpl;
import com.abhat.yifycleanarchitecture.presentation.view.MovieListView;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Anirudh Uppunda on 5/9/17.
 */

public class MoviePresenterImplTest {
    private MockView view;

    @Test
    public void shouldPassMovieListToView() {
        // given
        view = new MockView();
        UseCase<ApiResponseData> useCase = new MockUseCase();


        // when
        MoviePresenterImpl moviePresenter = new MoviePresenterImpl(view, useCase);
        moviePresenter.getMovieList("seeds", "", "");

        // then
        Assert.assertEquals(true, ((MockView)view).passed);
    }

    private class MockView implements MovieListView {
        boolean passed;
        @Override
        public void showProgress() {

        }

        @Override
        public void hideProgress() {

        }

        @Override
        public void displayMovieList(List<Movie> movies) {
            passed = true;
        }

        @Override
        public void displayError() {

        }
    }

    public class MockUseCase extends UseCase<ApiResponseData> {
        @Override
        public Observable<ApiResponseData> buildUseCase(String sortBy, String quality, String rating) {
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
                    view.displayMovieList(new ArrayList<Movie>());
                }
            });


            return observable;
        }
    }
}
