package com.example.julia.rombooking_v16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Julia on 27.05.2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Rombooking";
    private static final  int DATABASE_VERSION = 1;


    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DatabaseTables.OnCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       DatabaseTables.onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }
}
