package com.digiponic.mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;


public class DBUserAdapter {
    public static final String KEY_ROWID = "user_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "test.db";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE2 = "create table presence (user_id integer primary key, " + "datetime datetime, " + "photo blob);";
    private static final String DATABASE_CREATE = "create table users (user_id integer primary key autoincrement, " + "username text not null, " + "password text not null, " + "name text not null);";
    private Context context = null;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBUserAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE2);
            db.execSQL(DATABASE_CREATE);
            db.execSQL("insert into users values ('1','aku','123');");
            db.execSQL("insert into users values ('2', 'kamu', '456', 'Joko Pitoyo');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS presence");
            onCreate(db);
        }
    }

    public void open() throws SQLException {
        db = DBHelper.getWritableDatabase();
    }

    public void close() {
        DBHelper.close();
    }

    public long AddUser(String username, String password) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_PASSWORD, password);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean Register(String username, String password) throws SQLException {
        Cursor mCursor = db.rawQuery("insert into " + DATABASE_TABLE + "(username, password) values (?,?)", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE username=? AND password=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean presence(int user_id, String datetime, byte[] bytes) throws SQLException {
        Cursor mCursor = db.rawQuery("insert into" + " presence " + "(user_id, datetime, photo) values (?,?,?)", new String[]{String.valueOf(user_id), datetime, Arrays.toString(bytes)});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }
}
