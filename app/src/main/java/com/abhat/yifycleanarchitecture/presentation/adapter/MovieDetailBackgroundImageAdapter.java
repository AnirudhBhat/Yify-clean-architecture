package com.abhat.yifycleanarchitecture.presentation.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhat.yifycleanarchitecture.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anirudh Uppunda on 15/9/17.
 */

public class MovieDetailBackgroundImageAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> backgroundImages;
    private ImageView mImageView;
    private TextView mIndicator;

    public MovieDetailBackgroundImageAdapter(Context context, ArrayList<String> backgroundImages) {
        this.mContext = context;
        this.backgroundImages = backgroundImages;
    }

    public void setBackgroundImages(ArrayList<String> backgroundImages) {
        this.backgroundImages = backgroundImages;
    }

    @Override
    public int getCount() {
        return backgroundImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.background_image_layout, container, false);
        mImageView = (ImageView)layout.findViewById(R.id.image);
        mIndicator = (TextView)layout.findViewById(R.id.viewpager_indicator);
        container.addView(layout);
        mIndicator.setText((position + 1) + "/3");
        Glide.with(mContext).load(backgroundImages.get(position)).into(mImageView);
        return layout;
    }
}
