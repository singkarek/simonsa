package com.digiponic.mobile;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DB.db";
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;

    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //TODO Auto-generated construction stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO Auto-generated method stub
        String table1 = "create table users (user_id integer primary key autoincrement, username text, password text, name text);";
        String table2 = "create table presence (user_id integer, date datetime, lat varchar, long varchar, foreign key (user_id) references users(user_id));";
        String table3 = "create table store (store_id integer primary key autoincrement, seller_name text, store_name, address text, phone integer);";
        String table4 = "create table product (product_id text primary key, product_name text);";
        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(table3);
        db.execSQL(table4);
        String sql = "INSERT INTO users (user_id, username, password, name) VALUES (null, 'aku', '123', 'Fierda Stephen'),(null, 'kamu', '456', 'Piggy MacTavish');";
        String sql2 = "INSERT INTO product (product_id, product_name) VALUES ('MY001', 'MY - Pink Line'),('MY002', 'MY - Blue Line'),('MY003', 'MY - Orange Line'),('MY004', 'MY - Gold Line'),('MY005', 'MY - Brown Line'),('MY006', 'MY - Green Line');";
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + "users");
        db.execSQL("DROP TABLE IF EXISTS " + "presence");
        db.execSQL("DROP TABLE IF EXISTS " + "store");
        db.execSQL("DROP TABLE IF EXISTS " + "product");
        onCreate(db);
    }

    public boolean Login(String username, String password) throws SQLException {
        Cursor mCursor = db.rawQuery("SELECT * FROM " + "users" + " WHERE username=? AND password=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }
}
