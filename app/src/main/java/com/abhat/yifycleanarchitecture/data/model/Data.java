package com.abhat.yifycleanarchitecture.data.model;

import java.util.List;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class Data {
    private int movie_count;
    private int limit;
    private int page_number;
    private List<Movie> movies;

    public int getMovie_count() {
        return movie_count;
    }

    public void setMovie_count(int movie_count) {
        this.movie_count = movie_count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
