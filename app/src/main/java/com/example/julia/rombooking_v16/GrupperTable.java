package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class GrupperTable {

    public static final String GRUPPER_DATA_TABLE = "grupper";
    public static final String GRUPPE_KODE = "gruppe_kode"; // int
    public static final String BRUKER_KODE = "bruker_kode"; // varchar

    private static final String GRUPPER_DATA_TABLE_CREATE = "create table "
            + GRUPPER_DATA_TABLE
            + " (" + GRUPPE_KODE + " integer primary key autoincrement, "
            + BRUKER_KODE + " text" + ");";


    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(GRUPPER_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(GrupperTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + GRUPPER_DATA_TABLE);
        onCreate(database);


    }



}
