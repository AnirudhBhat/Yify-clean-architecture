package com.abhat.yifycleanarchitecture.data.repository;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Data;
import com.abhat.yifycleanarchitecture.data.model.Movie;

import rx.Observable;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public interface MovieRepository {
    Observable<ApiResponseData> movieList(String sortBy, String searchQuery);

    Observable<ApiResponseData> movieDetails(String id, String withImages, String withCast);
}
