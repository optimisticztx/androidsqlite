package com.example.zhangtianxiang15.Page;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhangtianxiang15.MyUtils.DatabaseHelper;
import com.example.zhangtianxiang15.MyUtils.Query;
import com.example.zhangtianxiang15.MyUtils.Student;
import com.example.zhangtianxiang15.MyUtils.StudentsAdapter;
import com.example.zhangtianxiang15.R;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 成绩报告页面
 */
public class DisplayStudent extends AppCompatActivity {
    private ListView listview;
    private Button bt_display_back;
    private ArrayList<Student> studentArrayList;
    private StudentsAdapter adapter;
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        databaseHelper = new DatabaseHelper(this, "ztx_db", null, 1);
        db = databaseHelper.getWritableDatabase();
        studentArrayList = new Query().query_student(db);
        initView();
    }

    public void initView() {
        bt_display_back = (Button) findViewById(R.id.bt_display_back);
        listview = (ListView) findViewById(R.id.listview);
        initData();
        adapter = new StudentsAdapter(this, listview);
        adapter.setData(studentArrayList);
        listview.setAdapter(adapter);
        bt_display_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initData() {
//        对学生进行排序（根据score降序排序）
        studentArrayList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                Double num2 = o2.getScore() - o1.getScore();
                if (num2 > 0) {
                    return 1;
                } else if (num2 < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for (int i = 0; i < studentArrayList.size(); i++) {
            Log.i("ztx", studentArrayList.get(i).toString());
        }
    }
}
