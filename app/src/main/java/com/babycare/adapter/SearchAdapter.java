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
import android.widget.Toast;

import com.babycare.R;
import com.babycare.activity.NaniBookingActivity;
import com.babycare.models.NanniesPojo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends BaseAdapter {
    List<NanniesPojo> ar9,ar1;
    Context cnt;
    public SearchAdapter(List<NanniesPojo> ar, Context cnt)
    {
        this.ar9=ar;
        this.cnt=cnt;
        this.ar1 = new ArrayList<NanniesPojo>();
        ar1.addAll(ar);
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
        View obj2=obj1.inflate(R.layout.list_nannies,null);

        ImageView ivImg=(ImageView)obj2.findViewById(R.id.iv);
        Glide.with(cnt)
                .load("http://babysitterprojectapp.com/BabySitter/"+ar1.get(pos).getImg_url())
                .into(ivImg);

        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText("Name  :"+ar1.get(pos).getName());

        TextView tv_phno=(TextView)obj2.findViewById(R.id.tv_phno);
        tv_phno.setText("Phone No  :"+ar1.get(pos).getPhone());

        TextView tv_emailid=(TextView)obj2.findViewById(R.id.tv_emailid);
        tv_emailid.setText("Email ID  :"+ar1.get(pos).getEmailid());

        TextView tv_locoation=(TextView)obj2.findViewById(R.id.tv_locoation);
        tv_locoation.setText("Locoation  :"+ar1.get(pos).getLocation());

        TextView tv_exper=(TextView)obj2.findViewById(R.id.tv_exper);
        tv_exper.setText("Experience  :"+ar1.get(pos).getExperience());

        TextView tv_rating=(TextView)obj2.findViewById(R.id.tv_rating);
        tv_rating.setText("Rating  :"+ar1.get(pos).getRating());

        Button btn_book=(Button)obj2.findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, NaniBookingActivity.class);
                intent.putExtra("ID",ar1.get(pos).getId());
                intent.putExtra("name",ar1.get(pos).getName());
                intent.putExtra("phno",ar1.get(pos).getPhone());
                intent.putExtra("Email",ar1.get(pos).getEmailid());
                intent.putExtra("Uname",ar1.get(pos).getUname());
                intent.putExtra("Experience",ar1.get(pos).getExperience());
                intent.putExtra("Locoation",ar1.get(pos).getLocation());
                intent.putExtra("User_type",ar1.get(pos).getUser_type());
                intent.putExtra("Rating",ar1.get(pos).getRating());
                intent.putExtra("utype",ar1.get(pos).getUser_type());
                cnt.startActivity(intent);

            }
        });
        return obj2;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ar1.clear();
        if (charText.length() == 0) {
            ar1.addAll(ar9);
        }
        else
        {
            for (NanniesPojo wp : ar9)
            {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)||wp.getPhone().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    ar1.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}