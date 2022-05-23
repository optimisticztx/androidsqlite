package com.example.zhangtianxiang15.Page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhangtianxiang15.R;

/**
 * 选择操作页面
 */
public class Selectaction extends AppCompatActivity {
    private Button bt_display, bt_add, bt_remove, bt_alter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectaction);
        initView();
    }

    public void initView() {
//        四个按钮四个页面
        bt_display = (Button) findViewById(R.id.bt_display);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_remove = (Button) findViewById(R.id.bt_remove);
        bt_alter = (Button) findViewById(R.id.bt_alter);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.bt_display:
                        startActivity(new Intent(Selectaction.this,
                                DisplayStudent.class));
                        break;
                    case R.id.bt_add:
                        startActivity(new Intent(Selectaction.this,
                                AddStudent.class));
                        break;
                    case R.id.bt_remove:
                        startActivity(new Intent(Selectaction.this,
                                RemoveStudent.class));
                        break;
                    case R.id.bt_alter:
                        startActivity(new Intent(Selectaction.this,
                                AlterScore.class));
                        break;
                    default:
                        break;
                }
            }
        };
        bt_display.setOnClickListener(listener);
        bt_add.setOnClickListener(listener);
        bt_remove.setOnClickListener(listener);
        bt_alter.setOnClickListener(listener);
    }
}
