package com.example.a13057.multipledialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AlertDialog.Builder(this)
                .setTitle("请添加兴趣爱好！")
                .setIcon(R.mipmap.ic_launcher)
                .setMultiChoiceItems(new String[]{"旅游","美食","汽车","宠物"},
                                                    null,null)
                .setPositiveButton("确定",null)
                .show();
    }
}
