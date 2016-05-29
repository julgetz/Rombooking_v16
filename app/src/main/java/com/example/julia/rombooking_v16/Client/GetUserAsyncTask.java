package com.example.julia.rombooking_v16.Client;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by sitha on 29.05.2016.
 */
public class GetUserAsyncTask extends AsyncTask<String, String, String> {

    protected String doInBackground(String... params) {
        String link = "https://android-rombooking-mbruksaas.c9users.io";
        try {
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            String urlString = params[0];


            request.setURI(new URI(link + urlString));

            client.execute(request);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
    }
}
