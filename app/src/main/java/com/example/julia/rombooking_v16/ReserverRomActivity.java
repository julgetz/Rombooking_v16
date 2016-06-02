package com.example.julia.rombooking_v16;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class ReserverRomActivity extends AppCompatActivity {

    private String rom_kode;
    private String fra;
    private String til;
    private EditText et_gruppemedlem;
    private EditText et_formal;
    private LinearLayout ll;
    private MsbDataSource ds;
    private final ArrayList<String> members = new ArrayList<>();

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver_rom);

        et_gruppemedlem = (EditText) findViewById(R.id.et_reserver_group);
        ll = (LinearLayout) findViewById(R.id.view_reserver_gruppemedlemmer);
        et_formal = (EditText) findViewById(R.id.et_reserver_formal);

        ds = new MsbDataSource(getApplicationContext());
        ds.open();

        Button btn_gruppe_add = (Button) findViewById(R.id.btn_reserver_group_add);
        assert btn_gruppe_add != null;
        btn_gruppe_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMember("-1");
            }
        });

        Button btn_bekreft = (Button) findViewById(R.id.bt_reserver_bekreft);
        assert btn_bekreft != null;
        btn_bekreft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserverRom("-1");
            }
        });

        Bundle b = getIntent().getExtras();
        if (b != null) {
            rom_kode = b.getString("rom_kode");
            fra = b.getString("fra");
            til = b.getString("til");

            Date fraDate = new Date(Long.valueOf(fra));
            TextView tv_fra = (TextView) findViewById(R.id.tv_reserver_tid_fra);
            assert tv_fra != null;
            tv_fra.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(fraDate));

            Date tilDate = new Date(Long.valueOf(til));
            TextView tv_til = (TextView) findViewById(R.id.tv_reserver_tid_til);
            assert tv_til != null;
            tv_til.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(tilDate));

            TextView tv_rom = (TextView) findViewById(R.id.tv_reserver_rom);
            assert tv_rom != null;
            tv_rom.setText(rom_kode);
        }

        members.add(ds.getLogin().getEmail());
    }

    private void addMember(String response) {
        if (!et_gruppemedlem.getText().toString().isEmpty()) {

            if(!isNetworkAvailable()) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.general_trenger_nettverk), Toast.LENGTH_LONG).show();
                vibrate();
                return;
            }

            switch (response) {
                case "-1":
                    et_gruppemedlem.setHint("");
                    if (!et_gruppemedlem.getText().toString().equals(ds.getLogin().getEmail())) {
                        new GetUserTask().execute(ds.getLogin().getEmail(), ds.getLogin().getSessionkeye(),
                                et_gruppemedlem.getText().toString());
                    } else {
                        et_gruppemedlem.setHint(getString(R.string.reserver_leggtilselv));
                        et_gruppemedlem.setText("");
                        vibrate();
                    }
                    break;
                case "Error: No user with specified epost found in DB":
                    et_gruppemedlem.setHint(getString(R.string.reserver_ugyldig_bruker));
                    et_gruppemedlem.setText("");
                    vibrate();
                    break;
                case "-3":
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.general_nettverksfeil), Toast.LENGTH_LONG).show();
                    vibrate();
                    break;
                default:
                    LayoutInflater inflater = getLayoutInflater();
                    final View medlem = inflater.inflate(R.layout.reserver_gruppe_medlem, ll, false);

                    ((TextView) medlem.findViewById(R.id.tv_reserver_group_member_name)).setText(
                            et_gruppemedlem.getText().toString());

                    Button btn_medlem_slett = (Button) medlem.findViewById(R.id.bt_reserver_group_del);
                    assert btn_medlem_slett != null;
                    btn_medlem_slett.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String epost = ((TextView) medlem.findViewById(
                                            R.id.tv_reserver_group_member_name)).getText().toString();
                                    members.remove(members.indexOf(epost));
                                    ll.removeView(medlem);
                                }
                            });

                    members.add(et_gruppemedlem.getText().toString());
                    ll.addView(medlem);
                    et_gruppemedlem.setHint("");
                    et_gruppemedlem.setText("");
                    break;
            }
        } else {
            vibrate();
        }
    }

    private void reserverRom(String response) {
        if (!et_formal.getText().toString().isEmpty()) {

            if(!isNetworkAvailable()) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.general_trenger_nettverk), Toast.LENGTH_LONG).show();
                vibrate();
                return;
            }

            switch (response) {
                case "-1":
                    String temp = "";
                    for (int i = 0; i < members.size(); i++)
                        temp += "&" + i + "=" + members.get(i);

                    new AddGroupTask().execute(ds.getLogin().getEmail(),
                            ds.getLogin().getSessionkeye(), temp);
                    break;
                case "-3":
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.general_nettverksfeil), Toast.LENGTH_LONG).show();
                    vibrate();
                    break;
                default:
                    new AddReservationTask().execute(ds.getLogin().getEmail(), ds.getLogin().getSessionkeye(),
                            fra, til, rom_kode, response, ds.getLogin().getEmail(), et_formal.getText().toString());
                    break;
            }
        } else {
            et_formal.setHint(getString(R.string.reserver_formal_ikke_tomt));
            et_formal.setText("");
            vibrate();
        }
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

    private class GetUserTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            //Params[0] = Epost
            //Params[1] = Sessionkey
            //Params[2] = UserEpost

            String link = "https://android-rombooking-mbruksaas.c9users.io/getUser.php";
            String paramString = "?epost=" + params[0] + "&sessionkey=" + params[1]
                    + "&getEpost=" + params[2];

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
            addMember(reading);
        }
    }

    private class AddGroupTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            //Params[0] = Epost
            //Params[1] = Sessionkey
            //Params[2] = Members-string

            String link = "https://android-rombooking-mbruksaas.c9users.io/groupManager.php";
            String paramString = "?epost=" + params[0] + "&sessionkey=" + params[1]
                    + params[2] + "&add=0";

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
            reserverRom(reading);
        }
    }

    private class AddReservationTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            //Params[0] = epost
            //Params[1] = sessionkey
            //Params[2] = fra
            //Params[3] = til
            //Params[4] = rom_kode
            //Params[5] = gruppe_kode
            //Params[6] = gruppe_leder
            //Params[7] = formal

            String link = "https://android-rombooking-mbruksaas.c9users.io/addReservation.php";
            String paramString = "?epost=" + params[0] + "&sessionkey=" + params[1]
                    + "&fra=" + params[2] + "&til=" + params[3]
                    + "&rom_kode=" + params[4] + "&gruppe_kode=" + params[5]
                    + "&gruppe_leder=" + params[6] + "&formal=" + params[7];

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
            if(reading.equals("-3")) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.general_nettverksfeil), Toast.LENGTH_LONG).show();
                vibrate();
            }

            Intent intent = new Intent(getApplicationContext(), ReservasjonerActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.book_rom:
                finish();
                break;
            case R.id.book_rom_dine_reservasjoner:
                Intent dineReservasjonerIntent = new Intent(this, ReservasjonerActivity.class);
                startActivity(dineReservasjonerIntent);
                finishAffinity();
                break;
            case R.id.logg_ut:
                MsbDataSource ds2 = new MsbDataSource(getApplicationContext());
                ds2.open();
                ds2.createLoginData(new Login());

                Intent loggutIntent = new Intent(this, LoginActivity.class);
                startActivity(loggutIntent);
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}