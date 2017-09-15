package com.abhat.yifycleanarchitecture.domain.usecases;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Data;
import com.abhat.yifycleanarchitecture.data.model.Movie;
import com.abhat.yifycleanarchitecture.data.repository.MovieNetworkSource;
import com.abhat.yifycleanarchitecture.data.repository.MovieRepository;

import rx.Observable;

/**
 * Created by Anirudh Uppunda on 15/9/17.
 */

public class GetMovieDetailUseCase extends UseCase<ApiResponseData> {
    private MovieRepository mMovieRepository;

    public GetMovieDetailUseCase() {
        mMovieRepository = new MovieNetworkSource();
    }

    @Override
    public Observable<ApiResponseData> buildUseCase(String sortBy, String quality) {
        return null;
    }

    @Override
    public Observable<ApiResponseData> buildMovieDetailUseCase(String id, String withImages, String withCast) {
        return mMovieRepository.movieDetails(id, withImages, withCast);
    }
}
