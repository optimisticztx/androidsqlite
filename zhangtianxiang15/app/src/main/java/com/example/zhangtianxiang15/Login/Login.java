package com.example.zhangtianxiang15.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zhangtianxiang15.MyUtils.DatabaseHelper;
import com.example.zhangtianxiang15.MyUtils.Query;
import com.example.zhangtianxiang15.R;
import com.example.zhangtianxiang15.Page.Selectaction;

import java.util.ArrayList;

/**
 * 登录页面（不要从这里运行，从MainActivity运行程序）
 */
public class Login extends AppCompatActivity {
    private ImageButton ib_clear1, ib_clear2;
    private EditText et_account, et_password;
    private Button bt_login, bt_register;
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;
    private ArrayList<String> accounts = null;
    private ArrayList<String> passwords = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this, "ztx_db", null, 1);
        db = databaseHelper.getWritableDatabase();
        initView();
    }
    public void initView() {
        ib_clear1 = (ImageButton) findViewById(R.id.ib_clear_account);
        ib_clear2 = (ImageButton) findViewById(R.id.ib_clear_password);
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_register = (Button) findViewById(R.id.bt_register);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                accounts = new Query().query_user(db, "account");
                passwords = new Query().query_user(db, "password");
                if (accounts.contains(account)) {
//                    如果密码正确
                    if (passwords.get(accounts.indexOf(account)).equals(password)) {
//                     进入系统
                        startActivity(new Intent(Login.this, Selectaction.class));
                    } else {
                        maketips("密码错误！！！");
                    }
                } else {
                    if (account.equals("")) {
                        maketips("请输入帐号！！！");
                    } else maketips("此账号不存在！！！");
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
                et_account.setText("");
                et_password.setText("");
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.ib_clear_account) et_account.setText("");
                else et_password.setText("");
                maketips("数据清除完毕！！！");
//                用来关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        };
        ib_clear1.setOnClickListener(listener);
        ib_clear2.setOnClickListener(listener);
    }

    public void maketips(String text) {
        Toast.makeText(Login.this,
                text,
                Toast.LENGTH_SHORT).show();
    }
}
