package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Julia on 28.05.2016.
 */
public class GruppenavnTable {

    public static final String GRUPPE_NAVN_DATA_TABLE = "gruppenavn";
    public static final String GRUPPE_KODE = "gruppe_kode";
    public static final String GRUPPENAVN = "gruppenavn";


    private static final String GRUPPE_NAVN_TABLE_CREATE = "create table"
            + GRUPPE_NAVN_DATA_TABLE
            + " (" + GRUPPE_KODE + "integer primary key autoincrement, "
            + GRUPPENAVN + " text"  + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(GRUPPE_NAVN_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(GruppenavnTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + GRUPPE_NAVN_DATA_TABLE);
        onCreate(database);
    }

}
