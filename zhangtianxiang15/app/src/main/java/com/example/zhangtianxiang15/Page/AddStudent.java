package com.example.zhangtianxiang15.Page;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhangtianxiang15.MyUtils.DatabaseHelper;
import com.example.zhangtianxiang15.MyUtils.Query;
import com.example.zhangtianxiang15.MyUtils.Student;
import com.example.zhangtianxiang15.R;

import java.util.ArrayList;

/**
 * 添加学生页面
 */
public class AddStudent extends AppCompatActivity {
    private Button bt_add_submit, bt_add_back;
    private EditText et_add_name, et_add_id, et_add_android, et_add_spark, et_add_datamining, et_add_echarts;
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;
    private ArrayList<Student> result = null;
    private ArrayList<String> id_list = new ArrayList<String>();
    private int len = 0;
    private String name = null;
    private String idd = null;
    private Double android = null;
    private Double spark = null;
    private Double datamining = null;
    private Double echarts = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        databaseHelper = new DatabaseHelper(this, "ztx_db", null, 1);
        db = databaseHelper.getWritableDatabase();
        result = new Query().query_student(db);
        for (int i = 0; i < result.size(); i++) {
            id_list.add(result.get(i).getId());
        }
        initView();
    }

    public void initView() {
        bt_add_submit = (Button) findViewById(R.id.bt_add_submit);
        bt_add_back = (Button) findViewById(R.id.bt_add_back);
        et_add_name = (EditText) findViewById(R.id.et_add_name);
        et_add_id = (EditText) findViewById(R.id.et_add_id);
        et_add_android = (EditText) findViewById(R.id.et_add_android);
        et_add_spark = (EditText) findViewById(R.id.et_add_spark);
        et_add_datamining = (EditText) findViewById(R.id.et_add_datamining);
        et_add_echarts = (EditText) findViewById(R.id.et_add_echarts);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.bt_add_submit:
                        getData();
                        if (id_list.contains(idd)) {
                            maketips("该学生已存在！！！");
                        } else if (idd.equals("")) {
                            maketips("请输入学号！！！");
                        } else {
                            if (name.equals("")) {
                                maketips("请输入姓名！！！");
                            }
                            ContentValues values = new ContentValues();
                            values.put("name", name);
                            values.put("id", idd);
                            values.put("android", Math.min(100.0, android));
                            values.put("spark", Math.min(100.0, spark));
                            values.put("datamining", Math.min(100.0, datamining));
                            values.put("echarts", Math.min(100.0, echarts));
                            db.insert("student", null, values);
                            cleardata();
                            maketips("添加成功！！！");
                        }
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        break;
                    case R.id.bt_add_back:
                        cleardata();
                        finish();
                        break;
                }
            }
        };
        bt_add_submit.setOnClickListener(listener);
        bt_add_back.setOnClickListener(listener);
    }

    public void getData() {
        name = et_add_name.getText().toString();
        idd = et_add_id.getText().toString();
        if (et_add_android.getText().toString().equals(""))
            android = 0.0;
        else
            android = Double.valueOf(et_add_android.getText().toString());
        if (et_add_spark.getText().toString().equals(""))
            spark = 0.0;
        else
            spark = Double.valueOf(et_add_spark.getText().toString());
        if (et_add_datamining.getText().toString().equals(""))
            datamining = 0.0;
        else
            datamining = Double.valueOf(et_add_datamining.getText().toString());
        if (et_add_echarts.getText().toString().equals(""))
            echarts = 0.0;
        else
            echarts = Double.valueOf(et_add_echarts.getText().toString());
    }
    public void cleardata() {
        et_add_name.setText("");
        et_add_id.setText("");
        et_add_android.setText("");
        et_add_spark.setText("");
        et_add_datamining.setText("");
        et_add_echarts.setText("");
    }

    public void maketips(String text) {
        Toast.makeText(AddStudent.this,
                text,
                Toast.LENGTH_SHORT).show();
    }
}

