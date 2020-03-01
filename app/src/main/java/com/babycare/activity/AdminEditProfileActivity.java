package com.babycare.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.babycare.R;
import com.babycare.Utils;
import com.babycare.models.AdminEditProfilePojo;
import com.babycare.models.EditProfilePojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminEditProfileActivity extends AppCompatActivity {
    EditText et_name, et_phno, et_experience, et_uname, et_password,et_locoation;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv_locoation,tv_nani_nur;
    Spinner spinner_nur_nani;
    Button btn_reg;
    ProgressDialog progressDialog;
    List<AdminEditProfilePojo> a1;
    ResponseData a2;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_editprofile);

        getSupportActionBar().setTitle("Nanies/Nurse EditProfile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "def-val");

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        tv4=(TextView)findViewById(R.id.tv4);
        tv5=(TextView)findViewById(R.id.tv5);
        tv6=(TextView)findViewById(R.id.tv6);
        tv_locoation=(TextView)findViewById(R.id.tv_locoation);
        tv_nani_nur=(TextView)findViewById(R.id.tv_nani_nur);


        btn_reg = (Button) findViewById(R.id.btn_reg);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password=(EditText)findViewById(R.id.et_password);
        et_phno = (EditText) findViewById(R.id.et_phno);
        et_experience = (EditText) findViewById(R.id.et_experience);
        et_uname = (EditText) findViewById(R.id.et_uname);
        et_locoation = (EditText) findViewById(R.id.et_locoation);

        spinner_nur_nani=(Spinner)findViewById(R.id.spinner_nur_nani);


        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        tv1.setTypeface(fontstyle);
        tv2.setTypeface(fontstyle);
        tv3.setTypeface(fontstyle);
        tv4.setTypeface(fontstyle);
        tv5.setTypeface(fontstyle);
        tv6.setTypeface(fontstyle);
        btn_reg.setTypeface(fontstyle);
        et_name.setTypeface(fontstyle);
        et_phno.setTypeface(fontstyle);
        et_experience.setTypeface(fontstyle);
        et_uname.setTypeface(fontstyle);
        et_password.setTypeface(fontstyle);


        progressDialog = new ProgressDialog(AdminEditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<AdminEditProfilePojo>> call = service.getCareTakerProfile(session);

        call.enqueue(new Callback<List<AdminEditProfilePojo>>() {
            @Override
            public void onResponse(Call<List<AdminEditProfilePojo>> call, Response<List<AdminEditProfilePojo>> response) {

                progressDialog.dismiss();
                a1 = response.body();
                // Toast.makeText(getApplicationContext(),""+response.body().size(),Toast.LENGTH_LONG).show();
                AdminEditProfilePojo user = a1.get(0);

                et_name.setText(user.getName());

                et_phno.setText(user.getPhone());

                et_experience.setText(user.getExperience());

                et_password.setText(user.getPwd());

                et_locoation.setText(user.getLocation());
            }

            @Override
            public void onFailure(Call<List<AdminEditProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AdminEditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();



            }
        });

    }

    private void submitData() {
        String name = et_name.getText().toString();
        String exp= et_experience.getText().toString();
        String phno = et_phno.getText().toString();
        String pwd = et_password.getText().toString();
        String loc = et_locoation.getText().toString();

        progressDialog = new ProgressDialog(AdminEditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        session = sharedPreferences.getString("user_name", "def-val");
        Toast.makeText(AdminEditProfileActivity.this, session, Toast.LENGTH_SHORT).show();


        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.admin_update_profile(name,phno, exp, loc, pwd, session);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                progressDialog.dismiss();
                a2 = response.body();
                AdminEditProfilePojo user = a1.get(0);

                et_name.setText(user.getName());

                et_phno.setText(user.getPhone());

                et_experience.setText(user.getExperience());

                et_password.setText(user.getPwd());

                et_locoation.setText(user.getLocation());

                if (response.body().status.equals("true")) {
                    Toast.makeText(AdminEditProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(AdminEditProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AdminEditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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