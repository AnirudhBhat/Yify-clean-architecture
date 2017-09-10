package com.abhat.yifycleanarchitecture.data.repository;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Data;
import com.abhat.yifycleanarchitecture.data.service.RestClient;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class MovieNetworkSource implements MovieRepository {
    @Override
    public Observable<ApiResponseData> movieList(String sortBy, String searchQuery) {
        return RestClient.getApiServiceForMovies().getMoviesList(sortBy, searchQuery, "50")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
