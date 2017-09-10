package com.abhat.yifycleanarchitecture.domain.usecases;

import com.abhat.yifycleanarchitecture.data.model.ApiResponseData;
import com.abhat.yifycleanarchitecture.data.model.Data;
import com.abhat.yifycleanarchitecture.data.repository.MovieNetworkSource;
import com.abhat.yifycleanarchitecture.data.repository.MovieRepository;
import com.abhat.yifycleanarchitecture.domain.UseCase;

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
    public Observable<ApiResponseData> buildUseCase(String sortBy, String searchQuery) {
        return mMovieRepository.movieList(sortBy, searchQuery);
    }
}
