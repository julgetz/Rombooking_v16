package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class CampusTable {

    public static final String CAMPUS_DATA_TABLE  = "campus";
    public static final String CAMPUS_ID = "campus_id"; // varchar
    public static final String CAMPUS_NAVN = "campus_navn"; //varchar


    private static final String CAMPUS_DATA_TABLE_CREATE = "create table"
            + CAMPUS_DATA_TABLE
            + " (" + CAMPUS_ID + " text primary key not null, "
            + CAMPUS_NAVN + " text" + ");";


    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CAMPUS_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(CampusTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + CAMPUS_DATA_TABLE);
        onCreate(database);
    }

}
