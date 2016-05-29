package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

/**
 * Created by Julia on 29.05.2016.
 */
public class Gruppenavn {

    private int gruppe_kode;
    private String gruppenavn;



    public Gruppenavn(){
        this.gruppe_kode = 0;
        this.gruppenavn = "";
    }


    public Gruppenavn(int gruppe_kode, String gruppenavn) {
        this.gruppe_kode = gruppe_kode;
        this.gruppenavn = gruppenavn;
    }

    public int getGruppe_kode() {
        return gruppe_kode;
    }

    public void setGruppe_kode(int gruppe_kode) {
        this.gruppe_kode = gruppe_kode;
    }

    public String getGruppenavn() {
        return gruppenavn;
    }

    public void setGruppenavn(String gruppenavn) {
        this.gruppenavn = gruppenavn;
    }

    public String toJsonString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return  json;
    }
}
