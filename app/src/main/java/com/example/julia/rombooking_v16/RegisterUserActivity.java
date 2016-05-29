package com.example.julia.rombooking_v16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.julia.rombooking_v16.Client.RequestData;

public class RegisterUserActivity extends AppCompatActivity {

    public RegisterUserActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    public void OnClick(View view) {
        RequestData req = new RequestData();
        EditText regFornavn = (EditText)findViewById(R.id.et_reg_fornavn);
        String fornavn = regFornavn.getText().toString();
        EditText regEtternavn = (EditText)findViewById(R.id.et_reg_etternavn);
        String etternavn = regEtternavn.getText().toString();
        EditText regEmail = (EditText)findViewById(R.id.et_reg_email);
        String email = regEmail.getText().toString();
        EditText regPassord = (EditText)findViewById(R.id.et_reg_passord);
        String passord = regPassord.getText().toString();

        String subFornavn = fornavn.substring(3);
        String subEtternavn = etternavn.substring(3);
        String brukerKode = subFornavn + subEtternavn;

        req.RegisterUser(fornavn, etternavn, email, passord, brukerKode);
    }
}
