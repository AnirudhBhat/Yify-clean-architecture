package com.abhat.yifycleanarchitecture.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abhat.yifycleanarchitecture.R;
import com.abhat.yifycleanarchitecture.data.model.Movie;
import com.abhat.yifycleanarchitecture.domain.usecases.GetMovieListUseCase;
import com.abhat.yifycleanarchitecture.presentation.adapter.MovieListAdapter;
import com.abhat.yifycleanarchitecture.presentation.presenter.MovieListPresenter;
import com.abhat.yifycleanarchitecture.presentation.presenter.MovieListPresenterImpl;
import com.abhat.yifycleanarchitecture.presentation.view.MovieListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class MovieListFragment extends Fragment implements MovieListView {

    private MovieListPresenter mMoviePresenter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar mProgressBar;
    private MovieListAdapter mAdapter;

    private String sortBy;
    private String searchQuery;

    public static MovieListFragment newInstance(String sortBy, String quality) {
        Bundle args = new Bundle();
        args.putString("sortBy", sortBy);
        args.putString("searchQuery", quality);
        MovieListFragment movieListFragment = new MovieListFragment();
        movieListFragment.setArguments(args);
        return movieListFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sortBy = getArguments().getString("sortBy");
        searchQuery = getArguments().getString("searchQuery");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.movie_recycler_view);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mAdapter = new MovieListAdapter(getActivity(), new ArrayList<Movie>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        showLoading();
        mMoviePresenter = new MovieListPresenterImpl(this, new GetMovieListUseCase());
        mMoviePresenter.getMovieList(sortBy, searchQuery);
        return view;
    }



    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayMovieList(List<Movie> movies) {
        hideLoading();
        mAdapter.setMovieList(movies);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayError() {
        hideLoading();
        Toast.makeText(getActivity(), "OOPS, something went wrong!. Please try again later...", Toast.LENGTH_LONG).show();
    }
}
