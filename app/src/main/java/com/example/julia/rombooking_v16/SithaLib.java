package com.example.julia.rombooking_v16;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;

@SuppressWarnings("WeakerAccess")
public class SithaLib {

    public void registerUser(String fornavn, String etternavn, String epost, String passord,
                             String bruker_kode, Context context) {
        String urlString = "/registerUser.php?fornavn=" + fornavn + "&etternavn=" + etternavn + "&epost=" + epost
                + "&passord=" + passord + "&bruker_kode=" + bruker_kode;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        new RequestTask().execute(contextUrl);
    }

    private class RequestTask extends AsyncTask<ContextUrl, String, String> {

        @Override
        protected String doInBackground(ContextUrl... params) {
            String link = "https://android-rombooking-mbruksaas.c9users.io";
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                ContextUrl contextUrl = params[0];
                String urlString = contextUrl.getUrl();

                request.setURI(new URI(link + urlString));

                client.execute(request);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    @SuppressWarnings("unused")
    public class ContextUrl {

        private final Context context;
        private final String url;

        public ContextUrl(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}