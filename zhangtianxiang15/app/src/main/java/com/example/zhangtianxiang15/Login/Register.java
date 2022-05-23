package com.example.zhangtianxiang15.Login;

import com.example.zhangtianxiang15.MyUtils.DatabaseHelper;
import com.example.zhangtianxiang15.MyUtils.Query;
import com.example.zhangtianxiang15.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * 注册帐号页面
 */
public class Register extends AppCompatActivity {
    private ImageButton ib_clear1, ib_clear2, ib_clear3;
    private EditText et_account1, et_password1, et_password2;
    private Button bt_register;
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper = new DatabaseHelper(this, "ztx_db", null, 1);
        db = databaseHelper.getWritableDatabase();
        initView();
    }

    public void initView() {
        ib_clear1 = (ImageButton) findViewById(R.id.ib_clear_account1);
        ib_clear2 = (ImageButton) findViewById(R.id.ib_clear_password1);
        ib_clear3 = (ImageButton) findViewById(R.id.ib_clear_password2);
        et_account1 = (EditText) findViewById(R.id.et_account1);
        et_password1 = (EditText) findViewById(R.id.et_password1);
        et_password2 = (EditText) findViewById(R.id.et_password2);
        bt_register = (Button) findViewById(R.id.bt_register1);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.ib_clear_account1) et_account1.setText("");
                else if (view.getId() == R.id.ib_clear_password1) et_password1.setText("");
                else et_password2.setText("");

                Toast.makeText(Register.this,
                        "数据清除完毕！！！",
                        Toast.LENGTH_SHORT).show();
//                用来关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        };
        ib_clear1.setOnClickListener(listener);
        ib_clear2.setOnClickListener(listener);
        ib_clear3.setOnClickListener(listener);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                String account = et_account1.getText().toString();
                String password1 = et_password1.getText().toString();
                String password2 = et_password2.getText().toString();
                ArrayList<String> accounts = new Query().query_user(db, "account");
                if (!account.equals("")) {
                    if (!accounts.contains(account)) {
                        if (password1.equals(password2)) {
                            if (password1.equals("")) {
                                maketips("请输入密码！！！");
                            } else {
                                ContentValues cValue = new ContentValues();
                                cValue.put("account", account);
                                cValue.put("password", password1);
                                db.insert("user", null, cValue);
                                maketips("帐号注册成功！！！");
                                finish();
                            }
                        } else {
                            maketips("请保证两次输入的密码相同！！！");
                        }
                    } else {
                        maketips("该账号已存在！！！");
                    }
                } else {
                    maketips("请输入帐号！！！");
                }
            }
        });
    }

    public void maketips(String text) {
        Toast.makeText(Register.this,
                text,
                Toast.LENGTH_SHORT).show();
    }
}
