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

public class UserDashBoardActivity extends AppCompatActivity {
    CardView cd_nannies,cd_nurse,cd_myprofile,cd_schedule;
    Button btn_search,btn_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashboard);

        getSupportActionBar().setTitle("User DashBoard");
        btn_search=(Button)findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        cd_nannies=(CardView)findViewById(R.id.cd_nannies);
        cd_nannies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,NanniesActivity.class);
                startActivity(intent);
            }
        });

        cd_nurse=(CardView)findViewById(R.id.cd_nurse);
        cd_nurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,NursesActivity.class);
                startActivity(intent);

            }
        });


        cd_myprofile=(CardView)findViewById(R.id.cd_myprofile);
        cd_myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,UserEditProfileActivity.class);
                startActivity(intent);

            }
        });

        cd_schedule=(CardView)findViewById(R.id.cd_schedule);
        cd_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,UserScheduleActivity.class);
                startActivity(intent);

            }
        });
        btn_logout=(Button)findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,LoginActivity.class);
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
