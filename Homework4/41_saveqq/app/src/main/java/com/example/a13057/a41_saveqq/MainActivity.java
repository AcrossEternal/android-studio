package com.example.a13057.a41_saveqq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Map;

/**
 * @author 13057
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1、初始化view
        initView();
        //2、如果用户保存了信息，进行数据的回显
        Map<String,String>userInfo= FileSaveQQ.getUserInfo(this);
        if(userInfo!=null){
            etNumber.setText(userInfo.get("number"));
            etPassword.setText(userInfo.get("password"));
        }
    }

    private void initView() {
        //1 完成控件的初始化
        etNumber = (EditText) findViewById(R.id.et_number);

        etPassword = (EditText) findViewById(R.id.et_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login);

        
        //2 给按钮设置点击事件
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        //1 点击按钮获取用户名
        String number =etNumber.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        //2 检查用户名密码是否为空
        if(TextUtils.isEmpty(number)){
            Toast.makeText(this,"请输入QQ账户",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入QQ密码",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();

        //3 保存用户信息
        boolean isSaveSuccess=FileSaveQQ.saveUserInfo(this,number,password);
        if(isSaveSuccess){
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
        }
    }
}
