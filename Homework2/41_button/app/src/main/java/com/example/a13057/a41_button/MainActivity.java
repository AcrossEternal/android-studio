package com.example.a13057.a41_button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button myBtn_one;
    private Button myBtn_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过findViewById()初始化控件
        myBtn_one = (Button) findViewById(R.id.btn_one);
        myBtn_two = (Button) findViewById(R.id.btn_two);
//        //匿名内部类的方法实现按钮2的点击事件
//        myBtn_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myBtn_two.setText("按钮2已被点击");
//            }        });
        myBtn_one.setOnClickListener(this);
        myBtn_two.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        //具体判断点击的是哪个按钮
        switch(v.getId()){
            case R.id.btn_one: //代表点击第一个按钮
                myBtn_one.setText("按钮1已被点击");
                break;
            case R.id.btn_two: //代表点击第二个按钮
                myBtn_two.setText("按钮2已被点击");
                break;
        }
    }
//    //通过实现onClick()方法，实现按钮1的点击事件
//    public void click(View v){
//        myBtn_one.setText("按钮1已被点击");
//    }
}
