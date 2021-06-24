package com.example.a13057.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 13057 on 2021/3/25.
 */

public class MyDialog extends Dialog {
    private String dialogNmae;
    private TextView tvMsg;
    private Button btnOK;
    private Button btnCancel;
    public MyDialog(Context context,String dialogNmae){
        super(context);
        this.dialogNmae=dialogNmae;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题
        setContentView(R.layout.my_dialog);         //引入自定义对话框布局
        tvMsg =(TextView) findViewById(R.id.tv_msg);
        btnOK=(Button) findViewById(R.id.btn_ok);
        btnCancel=(Button) findViewById(R.id.btn_cancel);
        tvMsg.setText(dialogNmae);      //设置自定义对话框显示内容
        //为“确定”按钮设置点击事件
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击“确定”按钮时的操作
            }
        });
        //为“取消”按钮设置点击事件
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();      //关闭当前对话框
            }
        });
    }
}
