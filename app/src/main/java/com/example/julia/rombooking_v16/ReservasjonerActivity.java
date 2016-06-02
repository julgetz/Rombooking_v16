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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservasjonerActivity extends AppCompatActivity {

    private ArrayList<Reservasjon> reservasjoner;
    private CustomReservasjonsAdapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasjoner);

        reservasjoner = new ArrayList<>();

        myCustomAdapter = new CustomReservasjonsAdapter(this,
                reservasjoner);

        ListView myListView = (ListView) findViewById(R.id.lv_mineres);
        assert myListView != null;
        myListView.setAdapter(myCustomAdapter);

        MsbDataSource ds = new MsbDataSource(getApplicationContext());
        ds.open();

        if(!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.general_trenger_nettverk), Toast.LENGTH_LONG).show();
            vibrate();
            return;
        }

        new ReservasjonsListTask().execute(ds.getLogin().getEmail(),
                ds.getLogin().getSessionkeye(), ds.getLogin().getEmail());
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 100, 100, 500};
        v.vibrate(pattern, -1);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void updateList(String rooms) {

        reservasjoner.clear();

        if (rooms.equals("-3")) {

            Toast.makeText(getApplicationContext(),
                    getString(R.string.general_nettverksfeil), Toast.LENGTH_LONG).show();
            vibrate();

        } else if(!rooms.equals("Error: User has no reservations in DB")) {

            Type type = new TypeToken<ArrayList<Reservasjon>>() {}.getType();
            ArrayList<Reservasjon> dlList = new Gson().fromJson(rooms, type);

            //Add() must be called for the ListView to update
            for (Reservasjon r : dlList)
                reservasjoner.add(r);
        }
        myCustomAdapter.notifyDataSetChanged();
    }

    public class CustomReservasjonsAdapter extends ArrayAdapter<Reservasjon> {
        private final int res;
        private final Context context;

        public CustomReservasjonsAdapter(Context context, List<Reservasjon> items) {
            super(context, R.layout.mineres_list_item, items);
            this.res = R.layout.mineres_list_item;
            this.context = context;
        }

        @SuppressLint("SimpleDateFormat")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout resView;
            Reservasjon reserv = getItem(position);

            //Re-use of view
            if (convertView == null) {
                resView = new LinearLayout(context);
                LayoutInflater infl = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                infl.inflate(res, resView, true);
            } else {
                resView = (LinearLayout) convertView;
            }

            Date fraDate = null;
            Date tilDate = null;
            try {
                fraDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reserv.getFra());
                tilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reserv.getTil());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String fraString = new SimpleDateFormat("dd/MM/yy HH:mm").format(fraDate);
            String tilString = new SimpleDateFormat("dd/MM/yy HH:mm").format(tilDate);

            ((TextView) resView.findViewById(R.id.tv_mineres_item_rom)).setText(reserv.getRom_kode());
            ((TextView) resView.findViewById(R.id.tv_mineres_item_fra)).setText(fraString);
            ((TextView) resView.findViewById(R.id.tv_mineres_item_til)).setText(tilString);

            return resView;
        }
    }

    private class ReservasjonsListTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            //Params[0] = Epost
            //Params[1] = Sessionkey
            //Params[2] = Bruker-epost

            String link = "https://android-rombooking-mbruksaas.c9users.io/getReservation.php";
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
                    return "Error: User has no reservations in DB";
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
            updateList(reading);
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
                Intent romBookingIntent = new Intent(this, RomBookingActivity.class);
                startActivity(romBookingIntent);
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