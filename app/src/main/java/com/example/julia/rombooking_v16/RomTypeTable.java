package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class RomTypeTable {


    public static final String ROM_TYPE_DATA_TABLE = "romTypeTable";
    public static final String ROM_TYPE_KODE = "rom_type_kode";

    private static final String  ROM_TYPE_TABLE_CREATE = "create table"
            + ROM_TYPE_DATA_TABLE
            + " (" + ROM_TYPE_KODE + "text primary key not null " + ");";


    public static void onCreate(SQLiteDatabase database){
        database.execSQL(ROM_TYPE_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.v(RomTypeTable.class.getName(), "upgrading databse version "
        + oldVersion + "to" + newVersion
        + ", wich will destroy all data");

        database.execSQL("DROP TALBE IF EXSISTS " + ROM_TYPE_DATA_TABLE);
        onCreate(database);
    }




}
