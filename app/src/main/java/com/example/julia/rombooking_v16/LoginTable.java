package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;

public class LoginTable {
    public static final String LOGIN_DATA_TABLE = "login";
    public static final String EMAIL = "email";
    public static final String PASSORD = "passord";
    public static final String SESSIONKEY = "response";

    private static  final String LOGIN_DATA_TABLE_CREATE = "create table "
            + LOGIN_DATA_TABLE
            + " (" + EMAIL + " text primary key not null, "
            + PASSORD + " text, "
            + SESSIONKEY + " text not null" +  ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(LOGIN_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + LOGIN_DATA_TABLE);
        onCreate(database);
    }
}