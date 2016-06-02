package com.example.julia.rombooking_v16;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings({"deprecation", "BooleanMethodIsAlwaysInverted"})
@SuppressLint("SimpleDateFormat")
public class RomBookingActivity extends AppCompatActivity implements DialogInterface.OnClickListener,
        View.OnClickListener, rombookingListFragment.romListInterface {

    private int day;
    private int month;
    private int year;
    private TextView tvDato;
    private TextView tvTidFra;
    private TextView tvTidTil;
    private int hourFra;
    private int minFra;
    private int hourTil;
    private int minTil;
    private rombookingListFragment bookingFrag;
    private MsbDataSource ds;
    private String sisteFra;
    private String sisteTil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rom_booking);

        ds = new MsbDataSource(getApplicationContext());
        ds.open();

        tvDato = (TextView) findViewById(R.id.et_dato);
        tvTidFra = (TextView) findViewById(R.id.et_tid_fra);
        tvTidTil = (TextView) findViewById(R.id.et_tid_til);

        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        hourFra = cal.get(Calendar.HOUR_OF_DAY);
        minFra = cal.get(Calendar.MINUTE);
        hourTil = cal.get(Calendar.HOUR_OF_DAY);
        minTil = cal.get(Calendar.MINUTE);

        Button btnDato = (Button) findViewById(R.id.bt_dato);
        assert btnDato != null;
        btnDato.setOnClickListener(RomBookingActivity.this);

        Button btnTidFra = (Button) findViewById(R.id.bt_tid_fra);
        assert btnTidFra != null;
        btnTidFra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(RomBookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTidFra.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                }, hourFra, minFra, true);

                timePickerDialog.show();
            }
        });

        Button btnTidTil = (Button) findViewById(R.id.bt_tid_til);
        assert btnTidTil != null;
        btnTidTil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(RomBookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTidTil.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                }, hourTil, minTil, true);

                timePickerDialog.show();
            }
        });

        //Updates list-fragment with new timeframe
        bookingFrag = (rombookingListFragment) getFragmentManager().findFragmentById(R.id.rombookingFragment);
        Button btnVisLedige = (Button) findViewById(R.id.bt_vis_ledige);
        assert btnVisLedige != null;
        btnVisLedige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fraFormatert = getFraFormatert();
                String tilFormatert = getTilFormatert();

                if (!fraFormatert.isEmpty() && !tilFormatert.isEmpty()) {
                    if (Long.valueOf(fraFormatert) >= Long.valueOf(tilFormatert)) {
                        tvTidFra.setText("");
                        tvTidTil.setText("");
                    } else {

                        if(!isNetworkAvailable()) {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.general_trenger_nettverk), Toast.LENGTH_LONG).show();
                            vibrate();
                            return;
                        }

                        sisteFra = fraFormatert;
                        sisteTil = tilFormatert;

                        Login li = ds.getLogin();
                        bookingFrag.initListViewAndRefreshLayout(li.getEmail(), li.getSessionkeye(),
                                fraFormatert, tilFormatert);
                    }
                }
            }
        });
    }

    private String getFraFormatert() {
        String dato = tvDato.getText().toString();
        String fra = tvTidFra.getText().toString();
        String fraFormatert = "";

        if (!dato.isEmpty() && !fra.isEmpty()) {
            try {
                Date fraDate = new SimpleDateFormat("dd/MM/yyy HH:mm").parse(dato + " " + fra);

                fraFormatert = Long.toString(fraDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return fraFormatert;
    }

    private String getTilFormatert() {
        String dato = tvDato.getText().toString();
        String til = tvTidTil.getText().toString();
        String tilFormatert = "";

        if (!dato.isEmpty() && !til.isEmpty()) {
            try {
                Date tilDate = new SimpleDateFormat("dd/MM/yyy HH:mm").parse(dato + " " + til);

                tilFormatert = Long.toString(tilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return tilFormatert;
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

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @SuppressLint("SetTextI18n")
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            tvDato.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };

    @Override
    public void onClick(DialogInterface dialog, int which) {
        showDialog(0);
    }

    @Override
    public void onClick(View v) {
        showDialog(0);
    }

    @Override
    public void reserverRom(String rom_kode) {
        Intent intent = new Intent(this, ReserverRomActivity.class);
        intent.putExtra("rom_kode", rom_kode);
        intent.putExtra("fra", sisteFra);
        intent.putExtra("til", sisteTil);
        startActivity(intent);
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

            case R.id.book_rom_dine_reservasjoner:
                Intent dineReservasjonerIntent = new Intent(this, ReservasjonerActivity.class);
                startActivity(dineReservasjonerIntent);
                finish();
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