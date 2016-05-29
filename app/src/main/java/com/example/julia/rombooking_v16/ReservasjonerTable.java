package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Julia on 28.05.2016.
 */
public class ReservasjonerTable {




    public static final String RESERVASJONER_DATA_TABLE = "reservasjoner"; //int
    public  static final String RESERVASJONS_ID = "reservasjons_id"; // int
    public static final String FRA = "fra"; // date
    public static  final String TIL = "til"; // date
    public static final String GRUPPE_KODE = "gruppe_kode"; // int
    public static final String GRUPPE_LEDER = "gruppe_leder"; // varchar
    public static final String FORMAL = "formal"; // varchar

    public static final String ROM_KODE = "rom_kode"; // varchar


    private static final String RESERVASJONS_DATA_TABLE_CREATE = "create table"
            + RESERVASJONER_DATA_TABLE
            + " (" + RESERVASJONS_ID + " integer primary key autoincrement, "
            + FRA + " text, "
            + TIL + " text, "
            + ROM_KODE  + " text,"
            + GRUPPE_KODE + " integer, "
            + GRUPPE_LEDER + " text, "
            + FORMAL + " text" + ");";





    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(RESERVASJONS_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(ReservasjonerTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + RESERVASJONER_DATA_TABLE);
        onCreate(database);


    }

}
