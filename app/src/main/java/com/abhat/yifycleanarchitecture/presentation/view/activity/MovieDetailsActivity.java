package com.abhat.yifycleanarchitecture.presentation.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhat.yifycleanarchitecture.R;
import com.abhat.yifycleanarchitecture.data.model.Cast;
import com.abhat.yifycleanarchitecture.data.model.Movie;
import com.abhat.yifycleanarchitecture.domain.usecases.GetMovieDetailUseCase;
import com.abhat.yifycleanarchitecture.domain.usecases.GetMovieListUseCase;
import com.abhat.yifycleanarchitecture.presentation.adapter.MovieCastAdapter;
import com.abhat.yifycleanarchitecture.presentation.adapter.MovieDetailBackgroundImageAdapter;
import com.abhat.yifycleanarchitecture.presentation.presenter.MovieListPresenterImpl;
import com.abhat.yifycleanarchitecture.presentation.presenter.MoviePresenterImpl;
import com.abhat.yifycleanarchitecture.presentation.view.MovieDetailView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

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
    private String torrentHash1080p;
    private String torrentUrl1080p;
    private String movieName;
    private String quality;
    private String size;
    private String runtime;
    private String movieId;
    private String trailerCode;
    private ViewPager mViewPager;

    private TextView imdbRatingText;
    private TextView summaryText;
    private ImageView coverImageView;
    private ImageView backgroundImageView;
    private TextView sizeView;
    private TextView runtimeView;
    private Button torrentDownload;
    private Button torrentDownload1080p;
    private Button trailer;
    private String hashUrl;
    private MoviePresenterImpl mMoviePresenter;
    private MovieDetailBackgroundImageAdapter adapter;
    private MovieCastAdapter mMovieCastAdapter;
    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolBar;
    private String hashUrl1080p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        mViewPager = (ViewPager)findViewById(R.id.background_image);
        mRecyclerView = findViewById(R.id.casts_recyclerview);
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mToolBar = findViewById(R.id.toolbar);
        coverImageView = (ImageView)findViewById(R.id.cover_image);
        //backgroundImageView = (ImageView)findViewById(R.id.background_image);
        imdbRatingText = (TextView)findViewById(R.id.imdb_rating);
        summaryText = (TextView)findViewById(R.id.summary);
        sizeView = (TextView)findViewById(R.id.movieSize);
        runtimeView = (TextView)findViewById(R.id.runtime);
        torrentDownload = (Button)findViewById(R.id.download_torrent);
        torrentDownload1080p = findViewById(R.id.download_torrent_1080p);
        trailer = (Button)findViewById(R.id.movie_trailer);
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.youtube.com/watch?v=" + trailerCode));
                startActivity(i);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imdbRating = bundle.getString("imdbRating");
            backgroundImage = bundle.getString("backgroundImage");
            coverImage = bundle.getString("coverImage");
            summary = bundle.getString("summary");
            trailerCode = bundle.getString("trailer");
            torrentLink = bundle.getString("torrentLink");
            torrentHash = bundle.getString("torrentHash");
            torrentUrl = bundle.getString("torrentUrl");
            movieName = bundle.getString("movieName");
            size = bundle.getString("size");
            runtime = bundle.getString("runtime");
            quality = bundle.getString("quality");
            movieId = bundle.getString("movieid");
            if (getIntent().hasExtra("torrentHash1080p")) {
                torrentHash1080p = bundle.getString("torrentHash1080p");
                torrentUrl1080p = bundle.getString("torrentUrl1080p");
            }
        }
        adapter = new MovieDetailBackgroundImageAdapter(this, new ArrayList<String>());
        mViewPager.setAdapter(adapter);

        mMovieCastAdapter = new MovieCastAdapter(this, new ArrayList<Cast>());
        mRecyclerView.setAdapter(mMovieCastAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        android.transition.Transition sharedElementEnterTransition = getWindow().getSharedElementEnterTransition();
        sharedElementEnterTransition.addListener(new android.transition.Transition.TransitionListener() {
            @Override
            public void onTransitionStart(android.transition.Transition transition) {

            }

            @Override
            public void onTransitionEnd(android.transition.Transition transition) {
                imdbRatingText.setText(imdbRating);
                sizeView.setText("Size: " + size);
                runtimeView.setText("Runtime: " + runtime);

                Interpolator interpolator = new DecelerateInterpolator();


                View[] animatedViews = new View[] {
                        imdbRatingText, runtimeView, sizeView
                };
                for (int i = 0; i < animatedViews.length; ++i) {
                    View v = animatedViews[i];

                    // hide the view
                    v.setAlpha(0f);
                    // move the view down a little bit
                    v.setTranslationY(100);

                    v.animate()
                            // http://blog.danlew.net/2015/10/20/using-hardware-layers-to-improve-animation-performance/
                            .withLayer()
                            .alpha(1.0f)
                            .setDuration(500)
                            .translationY(0)
                            .setInterpolator(interpolator)
                            .setStartDelay(100 + 100 * i)
                            .start();
                }

            }

            @Override
            public void onTransitionCancel(android.transition.Transition transition) {

            }

            @Override
            public void onTransitionPause(android.transition.Transition transition) {

            }

            @Override
            public void onTransitionResume(android.transition.Transition transition) {

            }
        });
        summaryText.setText(summary);
        loadCoverImage();
        loadBackgroundImage();
        constructHashFor720p();
        constructHashFor1080p();

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

        torrentDownload1080p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(hashUrl1080p));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void constructHashFor720p() {
        //magnet:?xt=urn:btih:704C8E9951081C67AE08D318397BB1283892A4D0&dn=Mindhorn+%282016%29+%5B720p%5D+%5BYTS.AG%5D&tr=udp%3A%2F%2Fglotorrents.pw%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fp4p.arenabg.ch%3A1337&tr=udp%3A%2F%2Ftracker.internetwarriors.net%3A1337
        hashUrl = "magnet:?xt=urn:btih:" + torrentHash + "&dn=" + movieName.replace(" ", "+") + "+[" + quality + "]" +  "+[YTS.AG]&tr=udp://open.demonii.com:1337/announce&tr=udp://tracker.openbittorrent.com:80";
    }

    private void constructHashFor1080p() {
        //magnet:?xt=urn:btih:704C8E9951081C67AE08D318397BB1283892A4D0&dn=Mindhorn+%282016%29+%5B720p%5D+%5BYTS.AG%5D&tr=udp%3A%2F%2Fglotorrents.pw%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fp4p.arenabg.ch%3A1337&tr=udp%3A%2F%2Ftracker.internetwarriors.net%3A1337
        hashUrl1080p = "magnet:?xt=urn:btih:" + torrentHash1080p + "&dn=" + movieName.replace(" ", "+") + "+[" + quality + "]" +  "+[YTS.AG]&tr=udp://open.demonii.com:1337/announce&tr=udp://tracker.openbittorrent.com:80";
    }

    private void loadCoverImage() {
        loadImage(coverImage, coverImageView);
    }

    private void loadBackgroundImage() {
        //loadImage(backgroundImage, backgroundImageView);
    }

    private void loadImage(String load, final ImageView into) {
        Glide.with(this)
                .asBitmap()
                .load(load)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(final Bitmap resource, Transition<? super Bitmap> transition) {
                        into.setImageBitmap(resource);
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                int dominantColor = palette.getDominantColor(getResources().getColor(R.color.colorPrimary));
                                mCollapsingToolbarLayout.setContentScrimColor(dominantColor);
                            }
                        });
                    }
                });
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
        if (movie.getCast().size() <= 0) {
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mMovieCastAdapter.setmCasts(movie.getCast());
            mMovieCastAdapter.notifyDataSetChanged();
        }
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
