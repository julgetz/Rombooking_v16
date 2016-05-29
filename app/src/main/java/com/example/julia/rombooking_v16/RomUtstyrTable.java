package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Julia on 28.05.2016.
 */
public class RomUtstyrTable {

    public static final String ROM_UTSTYR_DATA_TABLE = "rom_ustyr";
    public static final String UTSTYR_KODE = "utstyr_kode"; // string
    public static final String ROM_KODE = "rom_kode"; // string


    private static  String ROM_UTSTYR_DATA_TABLE_CREATE = "create table "
            + ROM_UTSTYR_DATA_TABLE
            + " (" + UTSTYR_KODE + "INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, "
            + ROM_KODE + " text" + ");";


    public static void onCreate(SQLiteDatabase database){
        database.execSQL(ROM_UTSTYR_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.v(RomTypeTable.class.getName(), "upgrading databse version "
                + oldVersion + "to" + newVersion
                + ", wich will destroy all data");

        database.execSQL("DROP TALBE IF EXSISTS " + ROM_UTSTYR_DATA_TABLE);
        onCreate(database);
    }



}
