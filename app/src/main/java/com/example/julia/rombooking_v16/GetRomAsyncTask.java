package com.example.julia.rombooking_v16;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class GetRomAsyncTask extends AsyncTask<Rom, Void, String> {

    private Context context = null;
    private String RomSource = "https://android-rombooking-mbruksaas.c9users.io/getRoom.php?bruker_kode=cha113&sessionkey=88410792ec9beb0248f87e0f046ce23c";
    public RomBookingActivity romBookingActivity;


    GetRomAsyncTask(Context context, RomBookingActivity romBookingActivity){
        this.context = context;
        this.romBookingActivity = romBookingActivity;
    }


    @Override
    protected String doInBackground(Rom... params) {

        RomSource +=params[0].getRom_kode();

        URL romUrl;
        String stringText = "";

        try{
            romUrl = new URL(RomSource);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(romUrl.openStream()));
            String StringBuffer;

            while ((StringBuffer = bufferedReader.readLine())!= null){
                stringText += StringBuffer;
            }

            bufferedReader.close();

            //System.out.print(stringText);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringText;
    }

    @Override
    protected void onPostExecute(String s) {
        //romBookingActivity.returnRomdata(s);
        super.onPostExecute(s);
    }
}
