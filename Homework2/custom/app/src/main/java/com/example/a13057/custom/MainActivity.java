package com.example.a13057.custom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDialog myDialog=new MyDialog(this,"我是自定义的Dialog");
        myDialog.show();
    }
}
