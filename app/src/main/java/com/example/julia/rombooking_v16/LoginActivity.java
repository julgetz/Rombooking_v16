package com.example.julia.rombooking_v16;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private CheckBox huskmeg;
    private MsbDataSource ds;
    private Login li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.general_trenger_nettverk), Toast.LENGTH_LONG).show();
            vibrate();
            finish();
            return;
        }

        ds = new MsbDataSource(getApplicationContext());
        ds.open();

        //If the user has previously checked "remember me", log in automatically
        li = ds.getLogin();
        if (!li.getPassord().isEmpty()) {
            new LoginTask().execute(li.getEmail(), li.getPassord());
            return;
        }

        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.et_login_email);
        password = (EditText) findViewById(R.id.et_login_passord);
        huskmeg = (CheckBox) findViewById(R.id.cb_login_huskmeg);

        Button bt_login = (Button) findViewById(R.id.bt_login);
        assert bt_login != null;
        bt_login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                valider();
            }
        });

        Button btnReg = (Button) findViewById(R.id.bt_login_reg);
        assert btnReg != null;
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(getApplicationContext(), RegisterUserActivity.class);
                startActivity(regIntent);
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 100, 100, 500};
        v.vibrate(pattern, -1);
    }

    private void valider() {
        boolean allGood = true;

        //Checks validity of email, all emails contain @
        if (!email.getText().toString().contains("@")) {
            email.setHint(getString(R.string.reg_email_ugyldig));
            email.setText("");
            allGood = false;
        }

        //Checks length of password
        if (password.getText().toString().length() < 6) {
            password.setHint(getString(R.string.reg_passord_lengde));
            password.setText("");
            allGood = false;
        }

        if (allGood) {

            if (!isNetworkAvailable()) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.general_trenger_nettverk), Toast.LENGTH_LONG).show();
                vibrate();
                return;
            }

            new LoginTask().execute(email.getText().toString(), password.getText().toString());

        } else {
            vibrate();
        }
    }

    private void login(String response) {

        //Checks for error-codes
        switch (response) {
            case "-1":
                email.setHint(getString(R.string.login_ikke_verifisert));
                email.setText("");
                password.setHint("");
                password.setText("");
                vibrate();
                break;
            case "-2":
                email.setHint("");
                password.setHint(getString(R.string.login_match));
                password.setText("");
                vibrate();
                break;
            case "-3":
                email.setHint("");
                email.setText("");
                password.setHint("");
                password.setText("");
                Toast.makeText(getApplicationContext(),
                        getString(R.string.general_nettverksfeil), Toast.LENGTH_LONG).show();
                vibrate();
                break;
            default:
                if (!li.getPassord().isEmpty()) {
                    ds.createLoginData(new Login(li.getEmail(), li.getPassord(), response));
                } else {
                    if (huskmeg.isChecked())
                        ds.createLoginData(new Login(email.getText().toString(), password.getText().toString(), response));
                    else
                        ds.createLoginData(new Login(email.getText().toString(), "", response));
                }

                Intent intent = new Intent(this, RomBookingActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private class LoginTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            //Params[0] = Epost
            //Params[1] = Passord

            String link = "https://android-rombooking-mbruksaas.c9users.io/login.php";
            String paramString = "?epost=" + params[0] + "&passord=" + params[1];

            HttpURLConnection conn = null;
            StringBuilder serverResponse = new StringBuilder();

            try {
                URL url = new URL(link + paramString);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String line;
                    try {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));

                        while ((line = br.readLine()) != null)
                            serverResponse.append(line);

                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                } else {
                    return "-3";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    conn.disconnect();
            }

            return String.valueOf(serverResponse);
        }

        @Override
        protected void onPostExecute(String reading) {
            login(reading);
        }
    }
}