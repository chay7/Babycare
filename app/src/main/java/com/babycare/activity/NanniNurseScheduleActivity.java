package com.babycare.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.babycare.R;
import com.babycare.Utils;
import com.babycare.adapter.NanniNurseScheduleAdapter;
import com.babycare.adapter.UserScheduleAdapter;
import com.babycare.models.NaniesNursesSchedulePojo;
import com.babycare.models.SchedulePojo;
import com.babycare.models.UserSchedulePojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NanniNurseScheduleActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<UserSchedulePojo> a1;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userschedule);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("Schedule");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        serverData();
    }
    List<NaniesNursesSchedulePojo> al;
    public void serverData(){
        progressDialog = new ProgressDialog(NanniNurseScheduleActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");
        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<NaniesNursesSchedulePojo>> call = service.getNaniesNurseSchedule(uname);
        call.enqueue(new Callback<List<NaniesNursesSchedulePojo>>() {
            @Override
            public void onResponse(Call<List<NaniesNursesSchedulePojo>> call, Response<List<NaniesNursesSchedulePojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(NanniNurseScheduleActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    al = response.body();
                    list_view.setAdapter(new NanniNurseScheduleAdapter(al, NanniNurseScheduleActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<NaniesNursesSchedulePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NanniNurseScheduleActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override                                                                                                                    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

