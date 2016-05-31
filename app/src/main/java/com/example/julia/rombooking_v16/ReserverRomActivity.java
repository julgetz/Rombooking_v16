package com.example.julia.rombooking_v16;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReserverRomActivity extends AppCompatActivity  {

    private EditText et_formal;
    private String rom_kode;
    private String fra;
    private String til;

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
    }

    private void reserverRom() {
        // TODO: Reservere rom etter at gruppe er blitt laget via groupManager.php, for å få gruppe_kode
    }

    @Override
    protected void onStart() {
        super.onStart();

        //dataSource = new DataSource(this);
        //dataSource.open();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}