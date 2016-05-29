package com.example.julia.rombooking_v16;

import com.google.gson.Gson;


public class Grupper {

    private int gruppe_kode;
    private String bruker_kode;


    public Grupper(){
        this.gruppe_kode = 0;
        this.bruker_kode = "";
    }

    public String getBruker_kode() {
        return bruker_kode;
    }

    public void setBruker_kode(String bruker_kode) {
        this.bruker_kode = bruker_kode;
    }

    public int getGruppe_kode() {
        return gruppe_kode;
    }

    public void setGruppe_kode(int gruppe_kode) {
        this.gruppe_kode = gruppe_kode;
    }

    public String toJsonString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return  json;
    }
}
