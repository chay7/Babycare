package com.babycare.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.babycare.R;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NanniNurseRegistrationActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    EditText et_name, et_phno, et_experience, et_uname, et_password,et_locoation,et_EmailID;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv_locoation,tv_nani_nur,tvEmailID;
    Spinner spinner_nur_nani;
    Button btn_reg;
    ProgressDialog progressDialog;
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://babysitterprojectapp.com/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

        getSupportActionBar().setTitle("Nanies/Nurse Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        tv4=(TextView)findViewById(R.id.tv4);
        tv5=(TextView)findViewById(R.id.tv5);
        tv6=(TextView)findViewById(R.id.tv6);
        tvEmailID=(TextView)findViewById(R.id.tvEmailID);
        tv_locoation=(TextView)findViewById(R.id.tv_locoation);
        tv_nani_nur=(TextView)findViewById(R.id.tv_nani_nur);

        btn_reg = (Button) findViewById(R.id.btn_reg);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password=(EditText)findViewById(R.id.et_password);
        et_EmailID=(EditText)findViewById(R.id.et_EmailID);
        et_phno = (EditText) findViewById(R.id.et_phno);
        et_experience = (EditText) findViewById(R.id.et_experience);
        et_uname = (EditText) findViewById(R.id.et_uname);
        et_locoation = (EditText) findViewById(R.id.et_locoation);

        spinner_nur_nani=(Spinner)findViewById(R.id.spinner_nur_nani);

        Button btn_upload_img = (Button) findViewById(R.id.btn_upload_img);
        btn_upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });
        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        tv1.setTypeface(fontstyle);
        tv2.setTypeface(fontstyle);
        tv3.setTypeface(fontstyle);
        tv4.setTypeface(fontstyle);
        tv5.setTypeface(fontstyle);
        tv6.setTypeface(fontstyle);
        tvEmailID.setTypeface(fontstyle);
        btn_reg.setTypeface(fontstyle);
        et_name.setTypeface(fontstyle);
        et_phno.setTypeface(fontstyle);
        et_experience.setTypeface(fontstyle);
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
                uploadImageToServer();
            }
        });

    }

    private void submitData() {
        String name = et_name.getText().toString();
        String phno = et_phno.getText().toString();
        String email = et_EmailID.getText().toString();
        String uname = et_uname.getText().toString();
        String pwd = et_password.getText().toString();
        String exp = et_experience.getText().toString();
        String loc = et_locoation.getText().toString();


        progressDialog = new ProgressDialog(NanniNurseRegistrationActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.nanies_nurse_registration(name, phno, email, uname, pwd,exp,spinner_nur_nani.getSelectedItem().toString(),loc);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(NanniNurseRegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(NanniNurseRegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NanniNurseRegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, NanniNurseRegistrationActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, NanniNurseRegistrationActivity.this);
                //Toast.makeText(getApplicationContext(),filePath,Toast.LENGTH_SHORT).show();
                file = new File(filePath);

            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    File file;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, NanniNurseRegistrationActivity.this);
            file = new File(filePath);
            // uploadImageToServer();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {


    }
    ProgressDialog pd;
    private Uri uri;
    private void uploadImageToServer(){
        pd=new ProgressDialog(NanniNurseRegistrationActivity.this);
        pd.setTitle("Loading");
        pd.show();
        Map<String, String> map = new HashMap<>();
        map.put("name",et_name.getText().toString());
        map.put("phone",et_phno.getText().toString());
        map.put("emailid",et_EmailID.getText().toString());
        map.put("uname1",et_uname.getText().toString());
        map.put("pwd1",et_password.getText().toString());

        map.put("experience",et_experience.getText().toString());
        map.put("user_type",spinner_nur_nani.getSelectedItem().toString());
        map.put("location",et_locoation.getText().toString());

        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EndPointUrl uploadImage = retrofit.create(EndPointUrl.class);
        Call<ResponseData> fileUpload = uploadImage.nanies_nurse_registration1(fileToUpload, map);
        fileUpload.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                Toast.makeText(NanniNurseRegistrationActivity.this, "Data is submitted successfully. ", Toast.LENGTH_LONG).show();
                finish();
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(NanniNurseRegistrationActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}