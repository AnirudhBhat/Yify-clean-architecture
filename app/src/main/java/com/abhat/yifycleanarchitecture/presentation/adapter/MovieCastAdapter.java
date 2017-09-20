package com.abhat.yifycleanarchitecture.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhat.yifycleanarchitecture.R;
import com.abhat.yifycleanarchitecture.data.model.Cast;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anirudh Uppunda on 20/9/17.
 */

public class MovieCastAdapter extends RecyclerView.Adapter<MovieCastAdapter.MyViewHolder> {

    private Context mContext;
    private List<Cast> mCasts;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (CircleImageView)itemView.findViewById(R.id.circle_image);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }

    public MovieCastAdapter(Context context, List<Cast> casts) {
        this.mContext = context;
        this.mCasts = casts;
    }

    public void setmCasts(List<Cast> casts) {
        this.mCasts = casts;
    }

    @Override
    public MovieCastAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.casts_row_layout, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieCastAdapter.MyViewHolder holder, int position) {
        CircleImageView image = holder.image;
        TextView name = holder.name;

        Glide.with(mContext)
                .load(mCasts.get(position).getUrl_small_image())
                .into(image);
        name.setText(mCasts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }
}
