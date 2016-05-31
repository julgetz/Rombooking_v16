package com.example.julia.rombooking_v16;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private  EditText email;
    private EditText password;
    private TextView sessionkeye;
    private DataSource dataSource;






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
                valider();
            }
        });
    }

    private void valider() {
        boolean allGood = true;

        //Checks validity of email, all emails contain @
        if(!email.getText().toString().contains("@")) {
            email.setHint(getString(R.string.reg_email_ugyldig));
            email.setText("");
            allGood = false;
        }

        //Checks length of password
        if(password.getText().toString().length() < 6 || password.getText().toString().length() > 19) {
            password.setHint(getString(R.string.reg_passord_lengde));
            password.setText("");
            allGood = false;
        }

        if(allGood)
            new LoginTask().execute(email.getText().toString(), password.getText().toString());
    }

    private void login(String respone) {

        //Checks for error-codes
        if(respone == "-1") {

            email.setHint(getString(R.string.login_ikke_verifisert));
            email.setText("");
        } else {
            if (respone == "-2") {
                email.setHint(getString(R.string.login_match));
                email.setText("");
                password.setHint(getString(R.string.login_match));
                password.setText("");
            } else {
                if(respone == "-3"){
                    dataSource.createLoginData(new Login(email.getText().toString(), password.getText().toString(), sessionkeye.getText().toString()));
                    LoginTask loginTask = new LoginTask();
                    loginTask.onPostExecute(respone);
                    finish();
                    }


                }

            }

            }


            // TODO: Julia: Lagre epost, passord og sessionkey(respone) i lokal DB



    private class LoginTask extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... params) {
            //Params[0] = Epost
            //Params[1] = Passord

            String link = "https://android-rombooking-mbruksaas.c9users.io/login.php";

            HttpURLConnection conn = null;
            StringBuilder serverResponse = new StringBuilder();

            try {
                URL url = new URL(link);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                BufferedWriter wr = new BufferedWriter(
                        new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                wr.write("?epost=" + params[0] + "&passord=" + params[1]);
                wr.flush();
                wr.close();

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
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn!=null)
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





