package com.example.julia.rombooking_v16;

import com.google.gson.Gson;

public class Login {

    private String email;
    private String passord;
    private String sessionkeye;


    public Login() {
        this.email = "";
        this.passord = "";
        this.sessionkeye = "";
    }

    public Login(String email, String passord, String sessionkeye) {
        this.email = email;
        this.passord = passord;
        this.sessionkeye = sessionkeye;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getSessionkeye() {
        return sessionkeye;
    }

    public void setSessionkeye(String sessionkeye) {
        this.sessionkeye = sessionkeye;
    }

    public String toJSONString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

}
