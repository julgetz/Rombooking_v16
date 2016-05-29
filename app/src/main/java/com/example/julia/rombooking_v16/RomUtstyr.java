package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

/**
 * Created by Julia on 29.05.2016.
 */
public class RomUtstyr {

    private String utstyr_kode;
    private String rom_kode;


    public RomUtstyr(){
        this.utstyr_kode = "";
        this.rom_kode = "";
    }

    public RomUtstyr(String rom_kode, String utstyr_kode) {
        this.rom_kode = rom_kode;
        this.utstyr_kode = utstyr_kode;
    }

    public String getRom_kode() {
        return rom_kode;
    }

    public void setRom_kode(String rom_kode) {
        this.rom_kode = rom_kode;
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
        return  json;
    }
}
