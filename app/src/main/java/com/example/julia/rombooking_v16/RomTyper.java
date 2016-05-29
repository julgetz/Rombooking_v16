package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

/**
 * Created by Julia on 29.05.2016.
 */
public class RomTyper {
    private String rom_type_kode;

    public RomTyper(){
        this.rom_type_kode = "";
    }

    public RomTyper(String rom_type_kode) {
        this.rom_type_kode = rom_type_kode;
    }

    public String getRom_type_kode() {
        return rom_type_kode;
    }

    public void setRom_type_kode(String rom_type_kode) {
        this.rom_type_kode = rom_type_kode;
    }
    public String toJsonString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return  json;
    }
}
