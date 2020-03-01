package com.babycare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.babycare.R;
import com.babycare.activity.NaniBookingActivity;
import com.babycare.models.NanniesPojo;
import com.babycare.models.ReviewRatingPojo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ReviewRatingAdapter extends BaseAdapter {
    List<ReviewRatingPojo> ar1;
    Context cnt;
    public ReviewRatingAdapter(List<ReviewRatingPojo> ar, Context cnt)
    {
        this.ar1=ar;
        this.cnt=cnt;

    }
    @Override
    public int getCount() {
        return ar1.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup)
    {
        LayoutInflater obj1 = (LayoutInflater)cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2=obj1.inflate(R.layout.row_review_rating,null);


        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText("Name  :"+ar1.get(pos).getUname());

        TextView tv_review=(TextView)obj2.findViewById(R.id.tv_review);
        tv_review.setText("Review  :"+ar1.get(pos).getReview());

        TextView tv_rating=(TextView)obj2.findViewById(R.id.tv_rating);
        tv_rating.setText("Rating  :"+ar1.get(pos).getRating());

        return obj2;
    }


}