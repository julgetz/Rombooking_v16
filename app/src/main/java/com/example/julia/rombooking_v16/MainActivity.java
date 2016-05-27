package com.example.julia.rombooking_v16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.instillinger:

                break;
            case R.id.login:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.ins:
                break;
            case R.id.hjelp:
                break;
            case R.id.avslutt:
                break;
            case R.id.book_rom:
                Intent romBookingIntent = new Intent(this, RomBookingActivity.class);
                startActivity(romBookingIntent);
                break;
            case R.id.book_rom_res:
                Intent reserverIntent = new Intent(this,ReserverRomActivity.class);
                startActivity(reserverIntent);
                break;
            case R.id.book_rom_send_bekreftelse:
                Intent bekreftIntent = new Intent(this,SendBekreftelseActivity.class);
                startActivity(bekreftIntent);
                break;
            case R.id.book_rom_kvittering:
                Intent kvitteringIntent = new Intent(this,KvitteringActivity.class);
                startActivity(kvitteringIntent);
                break;

            case R.id.book_rom_dine_reservasjoner:
                Intent dineReservasjonerIntent = new Intent(this,ReservasjonerActivity.class);
                startActivity(dineReservasjonerIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}











