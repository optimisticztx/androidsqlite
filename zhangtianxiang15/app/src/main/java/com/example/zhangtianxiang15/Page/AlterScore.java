package com.example.zhangtianxiang15.Page;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhangtianxiang15.MyUtils.DatabaseHelper;
import com.example.zhangtianxiang15.MyUtils.Query;
import com.example.zhangtianxiang15.MyUtils.Student;
import com.example.zhangtianxiang15.R;

import java.util.ArrayList;

/**
 * 修改成绩页面
 */
public class AlterScore extends AppCompatActivity {
    private Button bt_alter_alter, bt_alter_back;
    private Button bt_alter_pre, bt_alter_next;
    private TextView tv_alter_name, tv_alter_id;
    private EditText et_alter_android, et_alter_spark, et_alter_datamining, et_alter_echarts;
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;
    private ArrayList<Student> result = null;
    private int idx = 0;
    private int len = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alter_score);
        databaseHelper = new DatabaseHelper(this, "ztx_db", null, 1);
        db = databaseHelper.getWritableDatabase();
        result = new Query().query_student(db);
        initView();
    }

    public void initView() {
        len = result.size();
        bt_alter_next = (Button) findViewById(R.id.bt_alter_next);
        bt_alter_pre = (Button) findViewById(R.id.bt_alter_pre);
        bt_alter_alter = (Button) findViewById(R.id.bt_alter_alter);
        bt_alter_back = (Button) findViewById(R.id.bt_alter_back);
        tv_alter_name = (TextView) findViewById(R.id.tv_alter_name);
        tv_alter_id = (TextView) findViewById(R.id.tv_alter_id);
        et_alter_android = (EditText) findViewById(R.id.et_alter_android);
        et_alter_spark = (EditText) findViewById(R.id.et_alter_spark);
        et_alter_datamining = (EditText) findViewById(R.id.et_alter_datamining);
        et_alter_echarts = (EditText) findViewById(R.id.et_alter_echarts);
        if (len != 0) setText();
        else clearText();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                len = result.size();
                int id = view.getId();
                switch (id) {
                    case R.id.bt_alter_back:
                        finish();
                        break;
                    case R.id.bt_alter_alter:
                        result.get(idx).setAndroid(
                                Double.valueOf(et_alter_android.getText().toString()));
                        result.get(idx).setSpark(
                                Double.valueOf(et_alter_spark.getText().toString()));
                        result.get(idx).setDatamining(
                                Double.valueOf(et_alter_datamining.getText().toString()));
                        result.get(idx).setEcharts(
                                Double.valueOf(et_alter_echarts.getText().toString()));
                        ContentValues values = new ContentValues();
                        values.put("android",
                                Math.min(Double.valueOf(et_alter_android.getText().toString()), 100.0));
                        values.put("spark",
                                Math.min(Double.valueOf(et_alter_spark.getText().toString()), 100.0));
                        values.put("datamining",
                                Math.min(Double.valueOf(et_alter_datamining.getText().toString()), 100.0));
                        values.put("echarts",
                                Math.min(Double.valueOf(et_alter_echarts.getText().toString()), 100.0));
                        String whereClause = "id=?";
                        String[] whereArgs = new String[]{tv_alter_id.getText().toString()};
                        db.update("student", values, whereClause, whereArgs);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        maketips("修改成功！");
                        break;
                    case R.id.bt_alter_pre:
                        idx -= 1;
                        if (idx < 0) idx = len - 1;
                        if (len != 0) setText();
                        else clearText();
                        break;
                    case R.id.bt_alter_next:
                        idx += 1;
                        if (idx >= len) idx = 0;
                        if (len != 0) setText();
                        else clearText();
                        break;
                }
            }
        };
        bt_alter_next.setOnClickListener(listener);
        bt_alter_pre.setOnClickListener(listener);
        bt_alter_back.setOnClickListener(listener);
        bt_alter_alter.setOnClickListener(listener);
    }

    public void setText() {
        tv_alter_name.setText(result.get(idx).getName());
        tv_alter_id.setText(result.get(idx).getId());
        et_alter_android.setText(String.valueOf(result.get(idx).getAndroid()));
        et_alter_spark.setText(String.valueOf(result.get(idx).getSpark()));
        et_alter_datamining.setText(String.valueOf(result.get(idx).getDatamining()));
        et_alter_echarts.setText(String.valueOf(result.get(idx).getEcharts()));
    }

    public void clearText() {
        tv_alter_name.setText("");
        tv_alter_id.setText("");
        et_alter_android.setText("");
        et_alter_spark.setText("");
        et_alter_datamining.setText("");
        et_alter_echarts.setText("");
    }

    public void maketips(String text) {
        Toast.makeText(AlterScore.this,
                text,
                Toast.LENGTH_SHORT).show();
    }

}
