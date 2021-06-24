package cn.itcast.registercard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView tvNickName;
    private TextView tvNumberShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();//初始化控件

        //获取MainActivity传递过来的数据
        Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        String nickName = intent.getStringExtra("nickName");

        //将获取到的数据显示到控件上
        tvNickName.setText("昵        称：" + nickName);
        tvNumberShow.setText("电话号码：" + number);
    }

    private void initView() {
        tvNickName = (TextView) findViewById(R.id.tv_nickName);
        tvNumberShow = (TextView) findViewById(R.id.tv_numberShow);
    }


}
