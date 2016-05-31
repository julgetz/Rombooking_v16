package com.example.julia.rombooking_v16;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.julia.rombooking_v16.Client.RequestData;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText fornavn;
    private EditText etternavn;
    private EditText email;
    private EditText bekreftEmail;
    private EditText passord;
    private EditText bekreftPassord;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        fornavn = (EditText) findViewById(R.id.et_reg_fornavn);
        etternavn = (EditText) findViewById(R.id.et_reg_etternavn);
        email = (EditText) findViewById(R.id.et_reg_email);
        bekreftEmail = (EditText) findViewById(R.id.et_reg_bekreft_email);
        passord = (EditText) findViewById(R.id.et_reg_passord);
        bekreftPassord = (EditText) findViewById(R.id.et_reg_bekreft_passord);

        context = getApplicationContext();

        Button btnReg = (Button) findViewById(R.id.bt_reg_registrer);
        assert btnReg != null;
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateFields()) {
                    RequestData req = new RequestData();

                    String subFornavn = fornavn.getText().toString().substring(0, 3);
                    String subEtternavn = etternavn.getText().toString().substring(0, 3);
                    String brukerKode = subFornavn + subEtternavn;

                    req.registerUser(fornavn.getText().toString(), etternavn.getText().toString(),
                            email.getText().toString(), passord.getText().toString(), brukerKode, context);
                }
            }
        });
    }

    private boolean validateFields() {
        boolean allGood = true;

        //Checks length
        if(fornavn.getText().toString().length() < 4 || fornavn.getText().toString().length() > 49) {
            fornavn.setHint(getString(R.string.reg_navn_mail_lengde));
            fornavn.setText("");
            allGood = false;
        }

        //Checks length
        if(etternavn.getText().toString().length() < 4 || etternavn.getText().toString().length() > 49) {
            etternavn.setHint(getString(R.string.reg_navn_mail_lengde));
            etternavn.setText("");
            allGood = false;
        }

        //Checks validity of email, all emails contain @
        if(!email.getText().toString().contains("@")) {
            email.setHint(getString(R.string.reg_email_ugyldig));
            email.setText("");
            allGood = false;
        }

        //Checks length
        if(email.getText().toString().length() == 0 || email.getText().toString().length() > 49) {
            email.setHint(getString(R.string.reg_navn_mail_lengde));
            email.setText("");
            allGood = false;
        }

        //Checks validity of email, all emails contain @
        if(!bekreftEmail.getText().toString().contains("@")) {
            bekreftEmail.setHint(getString(R.string.reg_email_ugyldig));
            bekreftEmail.setText("");
            allGood = false;
        }

        //Checks length
        if(bekreftEmail.getText().toString().length() == 0 || bekreftEmail.getText().toString().length() > 49) {
            bekreftEmail.setHint(getString(R.string.reg_navn_mail_lengde));
            bekreftEmail.setText("");
            allGood = false;
        }

        //Checks if the emails match
        if(!email.getText().toString().equals(bekreftEmail.getText().toString())) {
            email.setHint(getString(R.string.reg_email_match));
            bekreftEmail.setHint(getString(R.string.reg_email_match));
            email.setText("");
            bekreftEmail.setText("");
            allGood = false;
        }

        //Checks length
        if(passord.getText().toString().length() < 6 || passord.getText().toString().length() > 19) {
            passord.setHint(getString(R.string.reg_passord_lengde));
            passord.setText("");
            allGood = false;
        }

        //Checks length
        if(bekreftPassord.getText().toString().length() < 6 || bekreftPassord.getText().toString().length() > 19) {
            bekreftPassord.setHint(getString(R.string.reg_passord_lengde));
            bekreftPassord.setText("");
            allGood = false;
        }

        //Checks if the passwords match
        if(!passord.getText().toString().equals(bekreftPassord.getText().toString())) {
            passord.setHint(getString(R.string.reg_passord_match));
            bekreftPassord.setHint(getString(R.string.reg_passord_match));
            passord.setText("");
            bekreftPassord.setText("");
            allGood = false;
        }

        return allGood;
    }
}