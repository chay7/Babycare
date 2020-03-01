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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.babycare.R;
import com.babycare.Utils;
import com.babycare.models.EditProfilePojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserEditProfileActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String session;
    EditText et_name, et_phno, et_uname, et_password, et_email;
    TextView tv1, tv2, tv4, tv5, tv3;
    Button btn_reg;
    ProgressDialog progressDialog;
    List<EditProfilePojo> a1;
    ResponseData a2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usereditprofile);
        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);

        session = sharedPreferences.getString("user_name", "def-val");
        //Toast.makeText(AdminEditProfileActivity.this,session,Toast.LENGTH_SHORT).show();

        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv3 = (TextView) findViewById(R.id.tv3);

        btn_reg = (Button) findViewById(R.id.btn_reg);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phno = (EditText) findViewById(R.id.et_phno);
        et_uname = (EditText) findViewById(R.id.et_uname);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        et_uname.setText(session);
        et_uname.setFocusable(false);

        Typeface fontstyle = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Lato-Medium.ttf");
        tv1.setTypeface(fontstyle);
        tv2.setTypeface(fontstyle);
        tv4.setTypeface(fontstyle);
        tv5.setTypeface(fontstyle);
        btn_reg.setTypeface(fontstyle);
        et_name.setTypeface(fontstyle);
        et_phno.setTypeface(fontstyle);
        et_uname.setTypeface(fontstyle);
        et_password.setTypeface(fontstyle);
        et_email.setTypeface(fontstyle);
        tv3.setTypeface(fontstyle);


        progressDialog = new ProgressDialog(UserEditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<EditProfilePojo>> call = service.getUserProfile(session);

        call.enqueue(new Callback<List<EditProfilePojo>>() {
            @Override
            public void onResponse(Call<List<EditProfilePojo>> call, Response<List<EditProfilePojo>> response) {

                progressDialog.dismiss();
                a1 = response.body();
                // Toast.makeText(getApplicationContext(),""+response.body().size(),Toast.LENGTH_LONG).show();
                EditProfilePojo user = a1.get(0);

                et_name.setText(user.getName());

                et_phno.setText(user.getPhone());

                et_email.setText(user.getEmailid());

                et_password.setText(user.getPwd());
            }

            @Override
            public void onFailure(Call<List<EditProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserEditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        String email = et_email.getText().toString();
        String phno = et_phno.getText().toString();
        String pwd = et_password.getText().toString();

        progressDialog = new ProgressDialog(UserEditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        session = sharedPreferences.getString("user_name", "def-val");
        Toast.makeText(UserEditProfileActivity.this, session, Toast.LENGTH_SHORT).show();


        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.update_user_profile(name, phno, email, pwd, session);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                progressDialog.dismiss();
                a2 = response.body();
                EditProfilePojo user = a1.get(0);

                et_name.setText(user.getName());

                et_phno.setText(user.getPhone());

                et_email.setText(user.getEmailid());

                et_password.setText(user.getPwd());

                if (response.body().status.equals("true")) {
                    Toast.makeText(UserEditProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(UserEditProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserEditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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
/*

        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
*/
