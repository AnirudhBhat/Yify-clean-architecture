package com.abhat.yifycleanarchitecture.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.abhat.yifycleanarchitecture.R;
import com.abhat.yifycleanarchitecture.data.model.Movie;
import com.abhat.yifycleanarchitecture.presentation.view.activity.MovieDetailsActivity;
import com.abhat.yifycleanarchitecture.presentation.view.activity.MovieListActivity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieListAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView)itemView.findViewById(R.id.movie_image);
        }
    }


    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_single_row, parent, false);


        MovieListAdapter.MyViewHolder myViewHolder = new MovieListAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MyViewHolder holder, final int position) {
        final ImageView image = holder.image;

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(context, MovieDetailsActivity.class);
                    i.putExtra("imdbRating", String.valueOf(movieList.get(position).getRating()));
                    i.putExtra("backgroundImage", movieList.get(position).getBackground_image());
                    i.putExtra("coverImage", movieList.get(position).getLarge_cover_image());
                    i.putExtra("summary", movieList.get(position).getSummary());
                    i.putExtra("trailer", movieList.get(position).getYt_trailer_code());
                    i.putExtra("torrentLink", movieList.get(position).getTorrents().get(0).getUrl());
                    i.putExtra("torrentHash", movieList.get(position).getTorrents().get(0).getHash());
                    i.putExtra("torrentUrl", movieList.get(position).getTorrents().get(0).getUrl());
                    i.putExtra("size", movieList.get(position).getTorrents().get(0).getSize());
                    i.putExtra("runtime", String.valueOf(movieList.get(position).getRuntime()));
                    i.putExtra("movieName", movieList.get(position).getTitle_long());
                    i.putExtra("quality", movieList.get(position).getTorrents().get(0).getQuality());
                    i.putExtra("movieid", String.valueOf(movieList.get(position).getId()));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((MovieListActivity)context, image, "coverImage");
                    context.startActivity(i, options.toBundle());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Glide.with(context)
                .load(movieList.get(position).getLarge_cover_image())
                .into(image);
    }

    @Override
    public int getItemCount() {
        if (movieList != null) {
            return movieList.size();
        } else {
            return 0;
        }
    }
}
