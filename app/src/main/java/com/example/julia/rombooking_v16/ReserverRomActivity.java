package com.example.julia.rombooking_v16;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


public class ReserverRomActivity extends AppCompatActivity  {

   private Button Dato;
   private Button Fra;
   private Button Til;
   private EditText et_Dato;
    private EditText et_til;
    private EditText et_fra;
    private DataSource dataSource = null;
    private RomBookingActivity romBookingActivity;
    private EditText et_formal;
    private Button bt_bekreft;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver_rom);

        et_formal = (EditText)findViewById(R.id.et_reserver_formal);
        bt_bekreft = (Button)findViewById(R.id.bt_reserver_bekreft);
    }


    @Override
    protected void onStart() {
        super.onStart();

        dataSource = new DataSource(this);
        dataSource.open();
    }




    @Override
    protected void onStop() {
        super.onStop();
    }



    }












