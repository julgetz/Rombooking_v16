package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

/**
 * Created by Julia on 27.05.2016.
 */
public class Rom {
    private String rom_kode; //primørnøkkel
    private String rom_navn;
    private String rom_type_kode;
    private String utstyr_kode;
    private int kapasitet_und;
    private String campus_id;
    private int er_aktiv ;


    public Rom (){

        this.rom_kode = "";
        this.rom_navn = "";
        this.rom_type_kode = "";
        this.utstyr_kode = "";
        this.kapasitet_und = 0;
        this.campus_id = "NARVIK";
        this.er_aktiv = 0;

    }

    public Rom(String campus_id, int er_aktiv, int kapasitet_und, String rom_kode, String rom_navn, String rom_type_kode, String utstyr_kode) {
        this.campus_id = campus_id;
        this.er_aktiv = er_aktiv;
        this.kapasitet_und = kapasitet_und;
        this.rom_kode = rom_kode;
        this.rom_navn = rom_navn;
        this.rom_type_kode = rom_type_kode;
        this.utstyr_kode = utstyr_kode;
    }

    public String getCampus_id() {return campus_id;}

    public void setCampus_id(String campus_id) {this.campus_id = campus_id;}

    public int getEr_aktiv() {return er_aktiv;}

    public void setEr_aktiv(int er_aktiv) {this.er_aktiv = er_aktiv;}

    public int getKapasitet_und() {return kapasitet_und;}

    public void setKapasitet_und(int kapasitet_und) {this.kapasitet_und = kapasitet_und;}

    public String getRom_kode() {return rom_kode;}

    public void setRom_kode(String rom_kode) {this.rom_kode = rom_kode;}

    public String getRom_navn() {return rom_navn;}

    public void setRom_navn(String rom_navn) {this.rom_navn = rom_navn;}

    public String getRom_type_kode() {return rom_type_kode;}

    public void setRom_type_kode(String rom_type_kode) {this.rom_type_kode = rom_type_kode;}

    public String getUtstyr_kode() {return utstyr_kode;}

    public void setUtstyr_kode(String utstyr_kode) {this.utstyr_kode = utstyr_kode;}


    public String toJSONSTRING(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}

