package com.abhat.yifycleanarchitecture.presentation.view;

import com.abhat.yifycleanarchitecture.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public interface MovieListView extends BaseView{
    void displayMovieList(List<Movie> movies);
}
