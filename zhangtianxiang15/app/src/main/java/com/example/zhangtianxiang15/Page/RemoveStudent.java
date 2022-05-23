package com.example.zhangtianxiang15.Page;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhangtianxiang15.MyUtils.DatabaseHelper;
import com.example.zhangtianxiang15.MyUtils.Query;
import com.example.zhangtianxiang15.MyUtils.Student;
import com.example.zhangtianxiang15.R;

import java.util.ArrayList;

/**
 * 移除学生界面
 */
public class RemoveStudent extends AppCompatActivity {
    private Button bt_remove_next, bt_remove_pre, bt_remove_remove, bt_remove_back;
    private TextView tv_remove_name, tv_remove_id;
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;
    private ArrayList<Student> result = null;
    private int idx = 0;
    private int len = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_student);
        databaseHelper = new DatabaseHelper(this, "ztx_db", null, 1);
        db = databaseHelper.getWritableDatabase();
        result = new Query().query_student(db);
        initView();
    }

    public void initView() {
        bt_remove_next = (Button) findViewById(R.id.bt_remove_next);
        bt_remove_pre = (Button) findViewById(R.id.bt_remove_pre);
        bt_remove_remove = (Button) findViewById(R.id.bt_remove_remove);
        bt_remove_back = (Button) findViewById(R.id.bt_remove_back);
        tv_remove_name = (TextView) findViewById(R.id.et_remove_name);
        tv_remove_id = (TextView) findViewById(R.id.et_remove_id);
        len = result.size();
        if (len != 0) setText();
        else clearText();
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                len = result.size();
                int id = view.getId();
                switch (id) {
                    case R.id.bt_remove_back:
                        finish();//结束当前页面
                        break;
                    case R.id.bt_remove_remove:
                        if (len == 0) {
                            maketips("Nothing to do！");//如果没有学生的话提示信息
                        } else {
                            result.remove(idx);
                            String whereClause = "id=?";
                            String[] whereArgs = new String[]{tv_remove_id.getText().toString()};
                            db.delete("student", whereClause, whereArgs);
                            idx = 0;
                            result = new Query().query_student(db);
                            len = result.size();
                            if (len != 0) setText();
                            else clearText();
                        }
//                        收起软键盘
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        break;
                    case R.id.bt_remove_pre://上一个学生
                        idx -= 1;
                        if (idx < 0) idx = len - 1;
                        if (len != 0) setText();
                        else clearText();
                        break;
                    case R.id.bt_remove_next://下一个学生
                        idx += 1;
                        if (idx >= len) idx = 0;
                        if (len != 0) setText();
                        else clearText();
                        break;
                }
            }
        };
        bt_remove_next.setOnClickListener(listener);
        bt_remove_pre.setOnClickListener(listener);
        bt_remove_remove.setOnClickListener(listener);
        bt_remove_back.setOnClickListener(listener);
    }

    public void setText() {
        tv_remove_name.setText(result.get(idx).getName());
        tv_remove_id.setText(result.get(idx).getId());
    }

    public void clearText() {
        tv_remove_name.setText("");
        tv_remove_id.setText("");
    }

    public void maketips(String text) {
        Toast.makeText(RemoveStudent.this,
                text,
                Toast.LENGTH_SHORT).show();
    }
}
