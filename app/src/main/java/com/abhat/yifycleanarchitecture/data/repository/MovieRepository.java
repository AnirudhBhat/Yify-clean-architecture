package com.abhat.yifycleanarchitecture.data.repository;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Data;

import rx.Observable;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public interface MovieRepository {
    Observable<ApiResponseData> movieList(String sortBy, String searchQuery);
}
