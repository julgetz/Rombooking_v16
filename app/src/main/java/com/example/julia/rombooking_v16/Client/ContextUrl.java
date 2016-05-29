package com.example.julia.rombooking_v16.Client;

import android.content.Context;

/**
 * Created by sitha on 29.05.2016.
 */
public class ContextUrl {

    private Context context;
    private String url;

    public ContextUrl(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public Context getActiveContext() {
        return context;
    }

    public String getUrl() {
        return url;
    }
}
