package com.example.julia.rombooking_v16;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rom_booking);

        SearchView sv = (SearchView) findViewById(R.id.searchView);
        assert sv != null;
        sv.setQueryHint("Rom, kapasitet..");

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

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTidFra.setText(hourOfDay + ":" + minute);

                    }
                }, hourFra, minFra, true);
                timePickerDialog.setTitle("select time");
                timePickerDialog.show();
            }
        });

        Button btnTidTil = (Button) findViewById(R.id.bt_tid_til);
        assert btnTidTil != null;
        btnTidTil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(RomBookingActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTidTil.setText(hourOfDay + ":" + minute);
                    }
                }, hourTil, minTil, true);

                timePickerDialog.setTitle("select time");
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

                if(!getFraFormatert().isEmpty() && !getTilFormatert().isEmpty()) {
                    // TODO: Hente bruker_kode og sessionkey fra DB for å sende til fragment
                    bookingFrag.initListViewAndRefreshLayout("", "",
                            getFraFormatert(), getTilFormatert());
                }
            }
        });
    }

    private String getFraFormatert() {
        String dato = tvDato.getText().toString();
        String fra = tvTidFra.getText().toString();
        String fraFormatert = "";

        if(!dato.isEmpty() && !fra.isEmpty()) {

            try {
                Date fraDate = new SimpleDateFormat("dd/MM/yyy HH:mm").parse(dato + " " + fra);

                fraFormatert = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fraDate);
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

        if(!dato.isEmpty() && !til.isEmpty()) {

            try {
                Date fraDate = new SimpleDateFormat("dd/MM/yyy HH:mm").parse(dato + " " + til);

                tilFormatert = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fraDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return tilFormatert;
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @SuppressLint("SetTextI18n")
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            tvDato.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
        }
    };

    @Override
    public void onClick(DialogInterface dialog, int which) { showDialog(0); }

    @Override
    public void onClick(View v) { showDialog(0); }

    @Override
    public void reserverRom(String rom_kode) {
        Intent intent = new Intent(this, RomBookingActivity.class);
        intent.putExtra("rom_kode", rom_kode);
        intent.putExtra("fra", getFraFormatert());
        intent.putExtra("til", getTilFormatert());
        startActivity(intent);
    }
}