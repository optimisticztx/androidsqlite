package com.example.zhangtianxiang15.MyUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * databasehelper
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql_table_user = "create table IF NOT EXISTS user(" +
                "account text," +
                "password text" +
                ")";
        String sql_table_student = "create table IF NOT EXISTS student(" +
                "name text ," +
                "id text primary key," +
                "android float," +
                "spark float," +
                "datamining float," +
                "echarts float" +
                ")";
        db.execSQL(sql_table_user);
        db.execSQL(sql_table_student);
//        默认帐号密码
        db.execSQL("INSERT INTO user VALUES (?, ?)",
                new Object[]{"admin", "123456"});
//        默认student
        db.execSQL("INSERT INTO student VALUES (?,?,?,?,?,?)",
                new Object[]{"ztx", "19160302015", "100", "100", "100", "100"});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
