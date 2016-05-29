package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

/**
 * Created by Julia on 29.05.2016.
 */
public class Campus {

    private String campus_id;
    private String campus_navn;


    public Campus(){
        this.campus_id = "";
        this.campus_navn = "";
    }

    public Campus(String campus_id, String campus_navn) {
        this.campus_id = campus_id;
        this.campus_navn = campus_navn;
    }

    public String getCampus_id() {
        return campus_id;
    }

    public void setCampus_id(String campus_id) {
        this.campus_id = campus_id;
    }

    public String getCampus_navn() {
        return campus_navn;
    }

    public void setCampus_navn(String campus_navn) {
        this.campus_navn = campus_navn;
    }

    public String toJsonString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return  json;
    }
}
