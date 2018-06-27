package com.example.satish.quizup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "contacts.db";
    private static final String COLUMN_FNAME = "fname";
    private static final String COLUMN_LNAME = "lname";
    private static final String COLUMN_EMAIL = "email1";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_CONFIRMPASSWORD = "conpwd";
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " +
            "fname text not null , lname text not null, email1 text not null , pass text not null, conpwd text not null);";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_FNAME, c.getFname());
        values.put(COLUMN_LNAME, c.getLname());
        values.put(COLUMN_EMAIL, c.getEmail1());
        values.put(COLUMN_PASS, c.getPass());
        values.put(COLUMN_CONFIRMPASSWORD, c.getConpwd());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPassword(String email) {
        db = this.getReadableDatabase();
        String query = "select email, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
