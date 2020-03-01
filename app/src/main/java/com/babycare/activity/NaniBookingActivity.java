package com.babycare.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.babycare.R;
import com.babycare.Utils;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaniBookingActivity extends AppCompatActivity {
    TextView tv_name,tv_phno,tv_dob,tv_email,tv_experience,tv_location,tv_rating;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String date;
    Button btn_book;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanibooking);
        Toast.makeText(getApplicationContext(),getIntent().getStringExtra("utype"),Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle("Nanies/Nurses Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_book=(Button)findViewById(R.id.btn_book);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_email=(TextView)findViewById(R.id.tv_email);
        tv_phno=(TextView)findViewById(R.id.tv_phno);
        tv_dob=(TextView)findViewById(R.id.tv_dob);
        tv_experience=(TextView)findViewById(R.id.tv_location);
        tv_location=(TextView)findViewById(R.id.tv_experience);
        tv_rating=(TextView)findViewById(R.id.tv_rating);
        tv_name.setText("Name  :"+getIntent().getStringExtra("name"));
        tv_phno.setText("Phone Number :"+getIntent().getStringExtra("phno"));
        tv_email.setText("Email :"+getIntent().getStringExtra("Email"));
        tv_experience.setText("Experience :"+getIntent().getStringExtra("Experience")+" Yrs");
        tv_location.setText("Locoation :"+getIntent().getStringExtra("Locoation"));
        tv_rating.setText("Rating :"+getIntent().getStringExtra("Rating"));
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timedatepicker();

            }
        });
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });



    }
    public void timedatepicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(String.valueOf(NaniBookingActivity.this), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                date = month + "/" + day + "/" + year;
                tv_dob.setText(date);


            }
        };
        DatePickerDialog dialog = new DatePickerDialog(
                NaniBookingActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String uname="";
    private void submitData() {
        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");
        progressDialog = new ProgressDialog(NaniBookingActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.add_schedule(tv_dob.getText().toString(),uname,getIntent().getStringExtra("name"),getIntent().getStringExtra("phno"),getIntent().getStringExtra("Email"),getIntent().getStringExtra("Uname"),getIntent().getStringExtra("utype"));
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(NaniBookingActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(NaniBookingActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NaniBookingActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
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
