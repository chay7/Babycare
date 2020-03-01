package com.babycare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.babycare.R;
import com.babycare.activity.NaniBookingActivity;
import com.babycare.activity.RatingReviewActivity;
import com.babycare.models.NursesPojo;
import com.babycare.models.SchedulePojo;
import com.babycare.models.UserSchedulePojo;

import java.util.List;

public class UserScheduleAdapter extends BaseAdapter {

    List<SchedulePojo> ar;
    Context cnt;
    public UserScheduleAdapter(List<SchedulePojo> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.row_user_schedule,null);

        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText("Name  :"+ar.get(pos).getNanies_nurse_name());

        TextView tv_phno=(TextView)obj2.findViewById(R.id.tv_phno);
        tv_phno.setText("Phone No  :"+ar.get(pos).getNanies_nurse_phone());

        TextView tv_date=(TextView)obj2.findViewById(R.id.tv_date);
        tv_date.setText("Date  :"+ar.get(pos).getSchedule_date());

        TextView tv_email=(TextView)obj2.findViewById(R.id.tv_email);
        tv_email.setText("Email  :"+ar.get(pos).getNanies_nurse_email());

        TextView tv_status=(TextView)obj2.findViewById(R.id.tv_status);
        tv_status.setText("Status : "+ar.get(pos).getStatus());

        Button btn_review_comment=(Button)obj2.findViewById(R.id.btn_review_comment);
        btn_review_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cnt,ar.get(pos).getNanie_nurse_uname(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(cnt, RatingReviewActivity.class);
                intent.putExtra("nuname",ar.get(pos).getNanie_nurse_uname());
                cnt.startActivity(intent);

            }
        });


        return obj2;

    }

}
