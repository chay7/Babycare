package com.babycare.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.babycare.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText et_name, et_phno, et_uname, et_password,et_EmailID;
    TextView tv1,tv2,tv4,tv5,tv4_EmailID;
    Button btn_reg;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv4=(TextView)findViewById(R.id.tv4);
        tv5=(TextView)findViewById(R.id.tv5);
        tv4_EmailID=(TextView)findViewById(R.id.tv4_EmailID);

        btn_reg = (Button) findViewById(R.id.btn_reg);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phno = (EditText) findViewById(R.id.et_phno);
        et_EmailID = (EditText) findViewById(R.id.et_EmailID);
        et_uname = (EditText) findViewById(R.id.et_uname);
        et_password = (EditText) findViewById(R.id.et_password);

        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        tv1.setTypeface(fontstyle);
        tv2.setTypeface(fontstyle);
        tv4.setTypeface(fontstyle);
        tv5.setTypeface(fontstyle);
        btn_reg.setTypeface(fontstyle);
        et_name.setTypeface(fontstyle);
        et_phno.setTypeface(fontstyle);
        et_uname.setTypeface(fontstyle);
        et_password.setTypeface(fontstyle);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (et_phno.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Phone Number Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (et_uname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "User Name Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (et_password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                submitData();
            }
        });

    }

    private void submitData() {
        String name = et_name.getText().toString();
        String phno = et_phno.getText().toString();
        String email = et_EmailID.getText().toString();
        String uname = et_uname.getText().toString();
        String pwd = et_password.getText().toString();


        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.user_registration(name, phno, email, uname, pwd);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
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
