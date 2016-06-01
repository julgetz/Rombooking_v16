package com.example.julia.rombooking_v16;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReserverRomActivity extends AppCompatActivity  {

    private EditText et_formal;
    private String rom_kode;
    private String fra;
    private String til;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver_rom);

        et_formal = (EditText) findViewById(R.id.et_reserver_formal);

        Button bt_bekreft = (Button) findViewById(R.id.bt_reserver_bekreft);
        assert bt_bekreft != null;
        bt_bekreft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserverRom();
            }
        });

        Bundle b = getIntent().getExtras();
        if(b != null) {
            rom_kode = b.getString("rom_kode");
            fra = b.getString("fra");
            til = b.getString("til");
        }

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

    private void reserverRom() {
        // TODO: Reservere rom etter at gruppe er blitt laget via groupManager.php, for å få gruppe_kode
    }
}