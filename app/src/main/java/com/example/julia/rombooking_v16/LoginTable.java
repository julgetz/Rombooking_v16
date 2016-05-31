package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LoginTable {
    public static final String LOGIN_DATA_TABLE = "login";
    public static final String EMAIL = "email";
    public static final String PASSORD = "passord";
    public static final String SESSIONKEY = "response";

    private static  final String LOGIN_DATA_TABLE_CREATE = "create table "
            + LOGIN_DATA_TABLE
            + " (" + EMAIL + " text primary key not null, "
            + PASSORD + " text, "
            + SESSIONKEY + " text " +  ");";

    public  static void onCreate(SQLiteDatabase database){
        database.execSQL(LOGIN_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.v(RomTable.class.getName(), "Upgrading database from version"
                + oldVersion + "to" + newVersion
                + ", swich will destroy all old data");

        database.execSQL("DROP TABLE IF EXISITS " + LOGIN_DATA_TABLE);
        onCreate(database);
    }
}