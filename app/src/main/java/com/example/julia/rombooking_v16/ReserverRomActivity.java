package com.example.julia.rombooking_v16;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class ReserverRomActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener {

    private Button btDato;
    private Button btnTidFra;
    private Button btnTidTil;
    private EditText etDato;
    private EditText etTidFra;
    private EditText etTidTil;

    private Calendar cal;

    private int day;
    private int month;
    private int year;

    private int hourFra;
    private int minFra;
    private int hourTil;
    private int minTil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver_rom);
        etDato = (EditText)findViewById(R.id.et_reserver_dato);

        btnTidFra = (Button)findViewById(R.id.bt_reserver_tid_fra);
        etTidFra = (EditText)findViewById(R.id.et_reserver_tid_fra);

        btnTidTil = (Button)findViewById(R.id.bt_reserver_tid_til);
        etTidTil = (EditText)findViewById(R.id.et_reserver_tid_til);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        btDato.setOnClickListener(this);

        hourFra = cal.get(Calendar.HOUR_OF_DAY);
        hourTil = cal.get(Calendar.HOUR_OF_DAY);

        minFra = cal.get(Calendar.MINUTE);
        minTil = cal.get(Calendar.MINUTE);

        btnTidFra.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(ReserverRomActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTidFra.setText(hourOfDay + ":" + minute);


                    }
                }, hourFra, minFra, true);
                timePickerDialog.setTitle("select time");
                timePickerDialog.show();
            }
        });

        btnTidTil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(ReserverRomActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTidTil.setText(hourOfDay + ":" + minute);


                    }
                }, hourTil, minTil, true);
                timePickerDialog.setTitle("select time");
                timePickerDialog.show();
            }
        });


        btDato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ReserverRomActivity.this,new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etDato.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }



                }, year, month,day);

                datePickerDialog.show();
            }
        });
    }



    @Override
    public void onClick(DialogInterface dialog, int which) {
        showDialog(0);
    }

    @Override
    public void onClick(View v) {
        showDialog(0);
    }



}

