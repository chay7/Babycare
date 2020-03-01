package com.babycare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.babycare.R;

public class AdminDashBoardActivity extends AppCompatActivity {
    CardView cd_schedule,cd_profile;
    Button btn_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        getSupportActionBar().setTitle("Nanies/Nurse DashBoard");
        cd_schedule=(CardView)findViewById(R.id.cd_schedule);
        cd_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,NanniNurseScheduleActivity.class);
                startActivity(intent);
            }
        });

        cd_profile=(CardView)findViewById(R.id.cd_profile);
        cd_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,AdminEditProfileActivity.class);
                startActivity(intent);

            }
        });
        btn_logout=(Button)findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,AdminLoginActivity.class);
                startActivity(intent);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idlogout:
                // Toast.makeText(getApplicationContext(),"logout Selected",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
