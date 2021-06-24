package com.example.a13057.progressdialog;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressDialog prodialog;                            //声明对话框
        prodialog =new ProgressDialog(this);   //构建对话框
        prodialog.setTitle("进度条对话框");
        prodialog.setIcon(R.mipmap.ic_launcher);
        prodialog.setMessage("正在下周请稍等");
        //设置水平进度条
        prodialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //圆形进度条
//        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prodialog.show();
    }
}
