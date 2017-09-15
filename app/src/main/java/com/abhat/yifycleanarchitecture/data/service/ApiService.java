package com.abhat.yifycleanarchitecture.data.service;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Data;
import com.abhat.yifycleanarchitecture.data.model.Movie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public interface ApiService {

    @GET("list_movies.json")
    Observable<ApiResponseData> getMoviesList(@Query("sort_by") String sortBy, @Query("query_term") String quality, @Query("limit") String limit);

    @GET("movie_details.json")
    Observable<ApiResponseData> getMovieDetails(@Query("movie_id") String id, @Query("with_images") String withImages, @Query("with_cast") String withCast);
}
