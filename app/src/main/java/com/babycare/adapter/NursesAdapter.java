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

import com.babycare.activity.NaniBookingActivity;
import com.babycare.activity.ViewReviewRatingActivity;
import com.babycare.models.NanniesPojo;
import com.babycare.R;
import com.babycare.models.NursesPojo;
import com.bumptech.glide.Glide;

import java.util.List;

public class NursesAdapter extends BaseAdapter {

    List<NursesPojo> ar;
    Context cnt;
    public NursesAdapter(List<NursesPojo> ar, Context cnt)
    {
        this.ar=ar;
        this.cnt=cnt;
    }
    @Override
    public int getCount() {
        return ar.size();
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
        View obj2=obj1.inflate(R.layout.list_nurses,null);

        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText("Name  :"+ar.get(pos).getName());

        ImageView ivImg=(ImageView)obj2.findViewById(R.id.iv);
        Glide.with(cnt)
                .load("http://babysitterprojectapp.com/BabySitter/"+ar.get(pos).getImg_url())
                .into(ivImg);

        TextView tv_phno=(TextView)obj2.findViewById(R.id.tv_phno);
        tv_phno.setText("Phone No  :"+ar.get(pos).getPhno());

        TextView tv_emailid=(TextView)obj2.findViewById(R.id.tv_emailid);
        tv_emailid.setText("Email  :"+ar.get(pos).getEmailid());

        TextView tv_exper=(TextView)obj2.findViewById(R.id.tv_exper);
        tv_exper.setText("Experience  :"+ar.get(pos).getExperience());

        TextView tv_locoation=(TextView)obj2.findViewById(R.id.tv_locoation);
        tv_locoation.setText("Locoation  :"+ar.get(pos).getLocation());

        TextView tv_rating=(TextView)obj2.findViewById(R.id.tv_rating);
        tv_rating.setText("Rating  :"+ar.get(pos).getRating());
        Button btn_review_rating=(Button)obj2.findViewById(R.id.btn_review_rating);
        btn_review_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, ViewReviewRatingActivity.class);
                intent.putExtra("uname",ar.get(pos).getUname());
                cnt.startActivity(intent);
                //Toast.makeText(cnt,ar.get(pos).getUname(),Toast.LENGTH_SHORT).show();
            }
        });
        Button btn_book=(Button)obj2.findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, NaniBookingActivity.class);
                intent.putExtra("ID",ar.get(pos).getId());
                intent.putExtra("name",ar.get(pos).getName());
                intent.putExtra("phno",ar.get(pos).getPhno());
                intent.putExtra("Email",ar.get(pos).getEmailid());
                intent.putExtra("Uname",ar.get(pos).getUname());
                intent.putExtra("Experience",ar.get(pos).getLocation());
                intent.putExtra("Locoation",ar.get(pos).getLocation());
                intent.putExtra("User_type",ar.get(pos).getUser_type());
                intent.putExtra("Rating",ar.get(pos).getRating());

                cnt.startActivity(intent);

            }
        });

       

        return obj2;

    }
  
}
