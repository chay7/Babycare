package com.babycare.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.babycare.adapter.NanniesAdapter;
import com.babycare.models.NanniesPojo;
import com.babycare.R;
import com.babycare.Utils;
import com.babycare.adapter.NursesAdapter;
import com.babycare.models.NursesPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NursesActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<NursesPojo> a1;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurses);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("Nurse");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view=(ListView)findViewById(R.id.list_view);

        a1= new ArrayList<>();

        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(NursesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<NursesPojo>> call = service.getNurses();
        call.enqueue(new Callback<List<NursesPojo>>() {
            @Override
            public void onResponse(Call<List<NursesPojo>> call, Response<List<NursesPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(NursesActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    list_view.setAdapter(new NursesAdapter(a1, NursesActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<NursesPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NursesActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    //add this method in your program
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


