package com.example.julia.rombooking_v16.Client;

import android.content.Context;
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
public class GetRoomAsyncTask extends AsyncTask<ContextUrl, String, String> {

    private Context context;

    protected String doInBackground(ContextUrl... params) {
        String link = "https://android-rombooking-mbruksaas.c9users.io";
        try {
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            ContextUrl contextUrl = params[0];
            String urlString = contextUrl.getUrl();
            context = contextUrl.getActiveContext();

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
