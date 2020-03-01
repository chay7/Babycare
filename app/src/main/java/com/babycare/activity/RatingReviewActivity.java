package com.babycare.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.babycare.R;
import com.babycare.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingReviewActivity  extends AppCompatActivity {
    EditText et_Review;
    RatingBar ratingBar;
    Button btn_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_rating);
        et_Review=(EditText)findViewById(R.id.et_Review);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
    }
    ProgressDialog progressDialog;
    private void submitData(){
        String review=et_Review.getText().toString();
        String rating=String.valueOf(ratingBar.getRating());;
        progressDialog = new ProgressDialog(RatingReviewActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        Call<ResponseData> call = service.review_rating(getIntent().getStringExtra("nuname"),sharedPreferences.getString("user_name", ""),rating,review);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                Toast.makeText(RatingReviewActivity.this,response.body().message,Toast.LENGTH_LONG).show();
                finish();
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RatingReviewActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
