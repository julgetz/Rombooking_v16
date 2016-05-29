package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

/**
 * Created by Julia on 29.05.2016.
 */
public class Reservasjon {

    private int reservasjons_id;
    private String fra;
    private String til;
    private String rom_kode;
    private int gruppe_kode;
    private String gruppe_leder;
    private String formal;


    public Reservasjon() {
        this.reservasjons_id = 0;
        this.fra = "";
        this.til = "";
        this.rom_kode = "";
        this.gruppe_kode = 0;
        this.gruppe_leder = "";
        this.formal = "";
    }

    public Reservasjon(String formal, String fra, int gruppe_kode, String gruppe_leder, int reservasjons_id, String rom_kode, String til) {
        this.formal = formal;
        this.fra = fra;
        this.gruppe_kode = gruppe_kode;
        this.gruppe_leder = gruppe_leder;
        this.reservasjons_id = reservasjons_id;
        this.rom_kode = rom_kode;
        this.til = til;
    }

    public String getFormal() {
        return formal;
    }

    public void setFormal(String formal) {
        this.formal = formal;
    }

    public String getFra() {
        return fra;
    }

    public void setFra(String fra) {
        this.fra = fra;
    }

    public int getGruppe_kode() {
        return gruppe_kode;
    }

    public void setGruppe_kode(int gruppe_kode) {
        this.gruppe_kode = gruppe_kode;
    }

    public String getGruppe_leder() {
        return gruppe_leder;
    }

    public void setGruppe_leder(String gruppe_leder) {
        this.gruppe_leder = gruppe_leder;
    }

    public int getReservasjons_id() {
        return reservasjons_id;
    }

    public void setReservasjons_id(int reservasjons_id) {
        this.reservasjons_id = reservasjons_id;
    }

    public String getRom_kode() {
        return rom_kode;
    }

    public void setRom_kode(String rom_kode) {
        this.rom_kode = rom_kode;
    }

    public String getTil() {
        return til;
    }

    public void setTil(String til) {
        this.til = til;
    }

    public String toJSONSTRING() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}