package com.example.zhangtianxiang15.MyUtils;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

//查询数据工具类
public class Query {
    public ArrayList<String> query_user(SQLiteDatabase db, String column) {
        ArrayList<String> arr = new ArrayList<String>();
        Cursor cursor = db.query("user", new String[]{column},
                null, null, null,
                null, null);
        //利用游标遍历所有数据对象
        while (cursor.moveToNext()) {
            arr.add(cursor.getString(cursor.getColumnIndex(column)));
        }
        return arr;
    }

    public ArrayList<Student> query_student(SQLiteDatabase db) {
        ArrayList<Student> arr = new ArrayList<Student>();

        Cursor cursor = db.query("student",
                new String[]{"name","id","android","spark","datamining","echarts"},
                null, null, null, null, null);
        //利用游标遍历所有数据对象
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String id = cursor.getString(cursor.getColumnIndex("id"));
            Double android = cursor.getDouble(cursor.getColumnIndex("android"));
            Double spark = cursor.getDouble(cursor.getColumnIndex("spark"));
            Double datamining = cursor.getDouble(cursor.getColumnIndex("datamining"));
            Double echarts = cursor.getDouble(cursor.getColumnIndex("echarts"));
            arr.add(new Student(name, id, android, spark, datamining, echarts));
        }
        return arr;
    }
}
