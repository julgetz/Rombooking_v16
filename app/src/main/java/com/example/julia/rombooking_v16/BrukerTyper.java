package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

/**
 * Created by Julia on 29.05.2016.
 */
public class BrukerTyper {

    private int bruker_type_id;
    private String bruker_type_navn;



    public BrukerTyper(){
        this.bruker_type_id = 0;
        this.bruker_type_navn = "";

    }

    public BrukerTyper(int bruker_type_id, String bruker_type_navn) {
        this.bruker_type_id = bruker_type_id;
        this.bruker_type_navn = bruker_type_navn;
    }

    public int getBruker_type_id() {
        return bruker_type_id;
    }

    public void setBruker_type_id(int bruker_type_id) {
        this.bruker_type_id = bruker_type_id;
    }

    public String getBruker_type_navn() {
        return bruker_type_navn;
    }

    public void setBruker_type_navn(String bruker_type_navn) {
        this.bruker_type_navn = bruker_type_navn;
    }

    public String toJsonString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return  json;
    }
}
