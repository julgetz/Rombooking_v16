package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

/**
 * Created by Julia on 29.05.2016.
 */
public class Utstyr {

    private String utstyr_kode;
    private String utstyr_navn;


    public Utstyr(){

        this.utstyr_navn = "";
        this.utstyr_navn = "";

    }


    public Utstyr(String utstyr_kode, String utstyr_navn){
        this.utstyr_kode = utstyr_kode;
        this.utstyr_navn = utstyr_navn;

    }

    public String getUtstyr_navn() {
        return utstyr_navn;
    }

    public void setUtstyr_navn(String utstyr_navn) {
        this.utstyr_navn = utstyr_navn;
    }

    public String getUtstyr_kode() {
        return utstyr_kode;
    }

    public void setUtstyr_kode(String utstyr_kode) {
        this.utstyr_kode = utstyr_kode;
    }

    public String toJsonString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }







}
