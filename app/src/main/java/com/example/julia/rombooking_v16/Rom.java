package com.example.julia.rombooking_v16;

/**
 * Created by Julia on 27.05.2016.
 */
public class Rom {

    private String rom_kode = "";
    private String rom_navn = "";
    private String rom_type_kode = "";
    private int kapasitet_und = 0;

    public Rom() {
        rom_kode = "";
        rom_navn = "";
        rom_type_kode = "";
        kapasitet_und = 0;
    }

    public Rom( int kapasitet_und, String rom_kode, String rom_navn, String rom_type_kode) {

        this.rom_kode = rom_kode;
        this.rom_navn = rom_navn;
        this.rom_type_kode = rom_type_kode;
        this.kapasitet_und = kapasitet_und;
    }

    public int getKapasitet_und() {return kapasitet_und;}

    public void setKapasitet_und(int kapasitet_und) {this.kapasitet_und = kapasitet_und;}

    public String getRom_kode() {return rom_kode;}

    public void setRom_kode(String rom_kode) {this.rom_kode = rom_kode;}

    public String getRom_navn() {return rom_navn;}

    public void setRom_navn(String rom_navn) {this.rom_navn = rom_navn;}

    public String getRom_type_kode() {return rom_type_kode;}

    public void setRom_type_kode(String rom_type_kode) {this.rom_type_kode = rom_type_kode;}
}

