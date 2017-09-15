package com.abhat.yifycleanarchitecture.presentation.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhat.yifycleanarchitecture.R;
import com.abhat.yifycleanarchitecture.data.model.Movie;
import com.abhat.yifycleanarchitecture.domain.usecases.GetMovieDetailUseCase;
import com.abhat.yifycleanarchitecture.domain.usecases.GetMovieListUseCase;
import com.abhat.yifycleanarchitecture.presentation.adapter.MovieDetailBackgroundImageAdapter;
import com.abhat.yifycleanarchitecture.presentation.presenter.MovieListPresenterImpl;
import com.abhat.yifycleanarchitecture.presentation.presenter.MoviePresenterImpl;
import com.abhat.yifycleanarchitecture.presentation.view.MovieDetailView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailView {

    private String imdbRating;
    private String backgroundImage;
    private String coverImage;
    private String summary;
    private String torrentLink;
    private String torrentHash;
    private String torrentUrl;
    private String movieName;
    private String quality;
    private String size;
    private String runtime;
    private String movieId;
    private ViewPager mViewPager;

    private TextView imdbRatingText;
    private TextView summaryText;
    private ImageView coverImageView;
    private ImageView backgroundImageView;
    private TextView sizeView;
    private TextView runtimeView;
    private Button torrentDownload;
    private String hashUrl;
    private MoviePresenterImpl mMoviePresenter;
    private MovieDetailBackgroundImageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        mViewPager = (ViewPager)findViewById(R.id.background_image);
        coverImageView = (ImageView)findViewById(R.id.cover_image);
        //backgroundImageView = (ImageView)findViewById(R.id.background_image);
        imdbRatingText = (TextView)findViewById(R.id.imdb_rating);
        summaryText = (TextView)findViewById(R.id.summary);
        sizeView = (TextView)findViewById(R.id.movieSize);
        runtimeView = (TextView)findViewById(R.id.runtime);
        torrentDownload = (Button)findViewById(R.id.download_torrent);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imdbRating = bundle.getString("imdbRating");
            backgroundImage = bundle.getString("backgroundImage");
            coverImage = bundle.getString("coverImage");
            summary = bundle.getString("summary");
            torrentLink = bundle.getString("torrentLink");
            torrentHash = bundle.getString("torrentHash");
            torrentUrl = bundle.getString("torrentUrl");
            movieName = bundle.getString("movieName");
            size = bundle.getString("size");
            runtime = bundle.getString("runtime");
            quality = bundle.getString("quality");
            movieId = bundle.getString("movieid");
        }
        adapter = new MovieDetailBackgroundImageAdapter(this, new ArrayList<String>());
        mViewPager.setAdapter(adapter);
        summaryText.setText(summary);
        imdbRatingText.setText(imdbRating);
        sizeView.setText("Size: " + size);
        runtimeView.setText("Runtime: " + runtime);
        loadCoverImage();
        loadBackgroundImage();
        constructHash();

        mMoviePresenter = new MoviePresenterImpl(this, new GetMovieDetailUseCase());
        mMoviePresenter.getMovieDetail(movieId);

        torrentDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(hashUrl));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void constructHash() {
        //magnet:?xt=urn:btih:704C8E9951081C67AE08D318397BB1283892A4D0&dn=Mindhorn+%282016%29+%5B720p%5D+%5BYTS.AG%5D&tr=udp%3A%2F%2Fglotorrents.pw%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fp4p.arenabg.ch%3A1337&tr=udp%3A%2F%2Ftracker.internetwarriors.net%3A1337
        hashUrl = "magnet:?xt=urn:btih:" + torrentHash + "&dn=" + movieName.replace(" ", "+") + "+[" + quality + "]" +  "+[YTS.AG]&tr=udp://open.demonii.com:1337/announce&tr=udp://tracker.openbittorrent.com:80";
    }

    private void loadCoverImage() {
        loadImage(coverImage, coverImageView);
    }

    private void loadBackgroundImage() {
        //loadImage(backgroundImage, backgroundImageView);
    }

    private void loadImage(String load, ImageView into) {
        Glide.with(this)
                .load(load)
                .into(into);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public void displayMovieDetails(Movie movie) {
        ArrayList<String> images = new ArrayList<String>();
        images.add(movie.getLarge_screenshot_image1());
        images.add(movie.getLarge_screenshot_image2());
        images.add(movie.getLarge_screenshot_image3());
        adapter.setBackgroundImages(images);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void displayError() {

    }
}
