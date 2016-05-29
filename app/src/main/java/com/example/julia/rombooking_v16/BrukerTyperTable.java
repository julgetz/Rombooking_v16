package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class BrukerTyperTable {

    public static final String BRUKER_TYPER_DATA_TABLE = "bruker_typer";
    public static final String BRUKER_TYPE_ID = "bruker_type_id";   // int
    public static final String BRUKER_TYPE_NAVN = "bruker_type_navn"; //varchar


    public static final String BRUKER_TYPER_DATA_TABLE_CREATE = "create table"
            + BRUKER_TYPER_DATA_TABLE
            + " (" + BRUKER_TYPE_ID + " integer primary key autoincrement, "
            + BRUKER_TYPE_NAVN + " text" + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(BRUKER_TYPER_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(BrukerTyperTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + BRUKER_TYPER_DATA_TABLE);
        onCreate(database);
    }
}