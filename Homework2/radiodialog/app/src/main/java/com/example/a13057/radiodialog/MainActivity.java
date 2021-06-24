package com.example.a13057.radiodialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //生成对话框
        new AlertDialog.Builder(this)
                .setTitle("请选择性别")              //设置标题
                .setIcon(R.mipmap.ic_launcher)      //设置图标
                .setSingleChoiceItems(new String[]{"男","女"},0,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which){

                            }
                        }
                )
                .setPositiveButton("确定",null)
                .show();
    }
}
