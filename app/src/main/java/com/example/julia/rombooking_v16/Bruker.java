package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

public class Bruker {

    private String bruker_kode;
    private String fornavn;
    private String etternavn;
    private String epost;
    private int bruker_type_id;
    private String passord;
    private String random;
    private int vertifisert;
    private String opprettet;


    public Bruker(){
        this.bruker_kode = "";
        this.fornavn = "";
        this.etternavn = "";
        this.epost = "";
        this.bruker_type_id = 0;
        this.random = "";
        this.vertifisert = 0;
        this.opprettet = "";
    }


    public Bruker(String bruker_kode, int bruker_type_id, String epost, String etternavn, String fornavn, String opprettet, String passord, String random, int vertifisert) {
        this.bruker_kode = bruker_kode;
        this.bruker_type_id = bruker_type_id;
        this.epost = epost;
        this.etternavn = etternavn;
        this.fornavn = fornavn;
        this.opprettet = opprettet;
        this.passord = passord;
        this.random = random;
        this.vertifisert = vertifisert;
    }

    public String getBruker_kode() {
        return bruker_kode;
    }

    public void setBruker_kode(String bruker_kode) {
        this.bruker_kode = bruker_kode;
    }

    public int getBruker_type_id() {
        return bruker_type_id;
    }

    public void setBruker_type_id(int bruker_type_id) {
        this.bruker_type_id = bruker_type_id;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getOpprettet() {
        return opprettet;
    }

    public void setOpprettet(String opprettet) {
        this.opprettet = opprettet;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public int getVertifisert() {
        return vertifisert;
    }

    public void setVertifisert(int vertifisert) {
        this.vertifisert = vertifisert;
    }

    public String toJsonString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return  json;
    }
}
