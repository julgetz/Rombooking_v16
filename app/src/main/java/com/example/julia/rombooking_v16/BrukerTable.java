package com.example.julia.rombooking_v16;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Julia on 28.05.2016.
 */
public class BrukerTable {

    public static final String BRUKER_DATA_TABLE = "brukere"; //varchar = TEXT
    public static final String BRUKER_KODE = "bruker_kode"; //primær nøkkel varchar = TEXT
    public static final String FORNAVN = "fornavn"; // varchar = TEXT
    public static final String ETTERNAVN = "etternavn"; // varcgar = TEXT
    public static final String EPOST = "epost"; //varcahar = TEXT
    public static final String BRUKER_TYPE_ID = "bruker_type_id"; //smalint = TEXT
    public static final String PASSORD = "passord"; // varchar = TEXT
    public static final String RANDOM = "random"; // varcgar = TEXT
    public static final String VERTIFISERT = "vertifisert"; // tinyint = INTEGER
    public static final String OPPRETTET = "opprettet"; // timestmap  = NUMERIC


    private static final String BRUKER_DATA_TABLE_CREATE = "create table"
            + BRUKER_DATA_TABLE
            + " (" + BRUKER_KODE + " text primary key not null, "
            + FORNAVN + " text, "
            + ETTERNAVN + " text, "
            + EPOST + " text, "
            + BRUKER_TYPE_ID + " integer, "
            + PASSORD + " text, "
            + RANDOM + " text, "
            + VERTIFISERT + " integer, "
            + OPPRETTET + " text " + ");";


    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(BRUKER_DATA_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(BrukerTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS '" + BRUKER_DATA_TABLE + "'");
        onCreate(database);


    }
}