package com.abhat.yifycleanarchitecture.presentation.view;

import com.abhat.yifycleanarchitecture.data.model.Movie;

/**
 * Created by Anirudh Uppunda on 15/9/17.
 */

public interface MovieDetailView extends BaseView {
    void displayMovieDetails(Movie movie);
}
