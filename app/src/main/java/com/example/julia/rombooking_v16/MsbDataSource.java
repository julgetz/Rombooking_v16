package com.example.julia.rombooking_v16;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

@SuppressWarnings("WeakerAccess")
public class MsbDataSource {

    private SQLiteDatabase database;
    private final MsbMySQLiteHelper dbHelper;

    public MsbDataSource(Context context) {
        dbHelper = new MsbMySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void createLoginData(Login login) {
        database.execSQL("DELETE FROM " + LoginTable.LOGIN_DATA_TABLE);

        ContentValues values = new ContentValues();
        values.put(LoginTable.EMAIL, login.getEmail());
        values.put(LoginTable.PASSORD, login.getPassord());
        values.put(LoginTable.SESSIONKEY, login.getSessionkeye());

        database.insert(LoginTable.LOGIN_DATA_TABLE, null, values);
    }

    public Login getLogin() {
        Login li = new Login();
        Cursor cursor = database.rawQuery("SELECT * FROM " + LoginTable.LOGIN_DATA_TABLE, null);

        if (cursor.moveToFirst()) {
            li.setEmail(cursor.getString(cursor.getColumnIndex(LoginTable.EMAIL)));
            li.setPassord(cursor.getString(cursor.getColumnIndex(LoginTable.PASSORD)));
            li.setSessionkeye(cursor.getString(cursor.getColumnIndex(LoginTable.SESSIONKEY)));
        }

        cursor.close();

        return li;
    }
}