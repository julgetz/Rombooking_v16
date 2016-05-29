package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Julia on 29.05.2016.
 */
public class UtstyrTable {

   public static final String UTSTYR_DATA_TABLE = "utstyr";
    public static final String UTSTYR_KODE = "utstyr_kode";
    public static final String UTSTYR_NAVN = "utstyr_navn";


    private static  final String UTSTYR_DATA_TABLE_CREATE = "create table"
            + UTSTYR_DATA_TABLE
            + " (" + UTSTYR_KODE + " text primary key not null"
            + UTSTYR_NAVN + " text"  + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(UTSTYR_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(UtstyrTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + UTSTYR_DATA_TABLE);
        onCreate(database);
    }
}


