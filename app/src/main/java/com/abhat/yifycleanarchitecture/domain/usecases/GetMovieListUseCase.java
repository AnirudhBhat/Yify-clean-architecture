package com.abhat.yifycleanarchitecture.domain.usecases;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.repository.MovieNetworkSource;
import com.abhat.yifycleanarchitecture.data.repository.MovieRepository;

import rx.Observable;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class GetMovieListUseCase extends UseCase<ApiResponseData> {

    private MovieRepository mMovieRepository;

    public GetMovieListUseCase() {
        mMovieRepository = new MovieNetworkSource();
    }


    @Override
    public Observable<ApiResponseData> buildUseCase() {
        return mMovieRepository.movieList(limit, searchQuery);
    }
}
