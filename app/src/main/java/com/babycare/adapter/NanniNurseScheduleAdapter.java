package com.babycare.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.babycare.R;
import com.babycare.Utils;
import com.babycare.activity.EndPointUrl;
import com.babycare.activity.NanniNurseScheduleActivity;
import com.babycare.activity.ResponseData;
import com.babycare.activity.RetrofitInstance;
import com.babycare.models.NaniesNursesSchedulePojo;
import com.babycare.models.UserSchedulePojo;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NanniNurseScheduleAdapter extends BaseAdapter {

    List<NaniesNursesSchedulePojo> ar;
    Context cnt;
    public NanniNurseScheduleAdapter(List<NaniesNursesSchedulePojo> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.list_nani_nurseschedule,null);
        ImageView iv=(ImageView)obj2.findViewById(R.id.iv);
        Glide.with(cnt)
                .load("http://babysitterprojectapp.com/BabySitter/"+ar.get(pos).getImg_url())
                .into(iv);

        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText("Name  :"+ar.get(pos).getName());

        TextView tv_phno=(TextView)obj2.findViewById(R.id.tv_phno);
        tv_phno.setText("Phone No  :"+ar.get(pos).getPhone());

        TextView tv_email=(TextView)obj2.findViewById(R.id.tv_email);
        tv_email.setText("Email  :"+ar.get(pos).getEmailid());

        TextView tv_date=(TextView)obj2.findViewById(R.id.tv_date);
        tv_date.setText("Date  :"+ar.get(pos).getSchedule_date());

        Button btn_accept=(Button)obj2.findViewById(R.id.btn_accept);
        Button btn_reject=(Button)obj2.findViewById(R.id.btn_reject);
        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverData(ar.get(pos).getId(),"Rejected");
            }
        });
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverData(ar.get(pos).getId(),"Accepted");
            }
        });
        return obj2;
    }
    ProgressDialog progressDialog;
    public void serverData(String id,String status){
        progressDialog = new ProgressDialog(cnt);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.updateScheduleStatus(id,status);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(cnt,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(cnt,"Status updated successfully",Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(cnt, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}