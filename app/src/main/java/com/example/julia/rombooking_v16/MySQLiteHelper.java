package com.example.julia.rombooking_v16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Rombookinga";
    private static final int DATABASE_VERSION = 7;


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        BrukerTable.onCreate(database);
        BrukerTyperTable.onCreate(database);
        GrupperTable.onCreate(database);
        GrupperTable.onCreate(database);
        ReservasjonerTable.onCreate(database);
        RomTable.onCreate(database);
        RomTypeTable.onCreate(database);
        RomUtstyrTable.onCreate(database);
        UtstyrTable.onCreate(database);
        CampusTable.onCreate(database);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        BrukerTable.onUpgrade(db, oldVersion, newVersion);
        BrukerTyperTable.onUpgrade(db, oldVersion, newVersion);
        GrupperTable.onUpgrade(db, oldVersion, newVersion);
        GrupperTable.onUpgrade(db, oldVersion, newVersion);
        ReservasjonerTable.onUpgrade(db, oldVersion, newVersion);
        RomTable.onUpgrade(db, oldVersion, newVersion);
        RomTypeTable.onUpgrade(db, oldVersion, newVersion);
        RomUtstyrTable.onUpgrade(db, oldVersion, newVersion);
        UtstyrTable.onUpgrade(db, oldVersion, newVersion);
        CampusTable.onUpgrade(db, oldVersion, newVersion);

    }
    @Override
    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        if (!database.isReadOnly()) {
            // Enable foreign key constraints
            database.execSQL("PRAGMA foreign_keys=ON;");


        } }
}

