package com.abhat.yifycleanarchitecture.domain.model;

/**
 * Created by Anirudh Uppunda on 10/9/17.
 */

public class MovieParams {
    private String sortBy;
    private String searchQuery;

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
