package com.example.julia.rombooking_v16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private  EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.et_login_email);
        password = (EditText) findViewById(R.id.et_login_passord);

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
                login();
            }
        });
    }

    private void login() {
        // TODO: sjekk login mot DB med silje sin kode, lagre sessionkey i lokal DB og send til RomBooking Activity
    }
}





