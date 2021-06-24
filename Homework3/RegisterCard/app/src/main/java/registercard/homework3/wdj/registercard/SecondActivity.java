package registercard.homework3.wdj.registercard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView tv_phone;
    private TextView tv_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //获取到Intent对象
        Intent intent=getIntent();
        //取出key对应的value值
        String phone=intent.getStringExtra("phone");
        String name=intent.getStringExtra("name");
        tv_phone=(TextView)findViewById(R.id.tv_phone);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_phone.setText("电话号码："+phone);
        tv_name.setText("昵        称："+name);
    }
}
