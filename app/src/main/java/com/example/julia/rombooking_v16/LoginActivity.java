package com.example.julia.rombooking_v16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnReg = (Button) findViewById(R.id.bt_login_reg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(getApplicationContext(), RegisterUserActivity.class);
                startActivity(regIntent);
            }
        });


        Button bt_login = (Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(getApplicationContext(), RomBookingActivity.class);
                startActivity(loginIntent);
            }
        });

    }


    }





