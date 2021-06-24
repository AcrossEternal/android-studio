package wdj.chapter7.startservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start=(Button)findViewById(R.id.btn_start);
        Button stop=(Button)findViewById(R.id.btn_stop);
    }
    //开启服务的方法
    public void start(View view) {
        Intent intent=new Intent(this, MyService.class);
        startService(intent);
    }
    //关闭服务的方法
    public void stop(View view) {
        Intent intent=new Intent(this, MyService.class);
        stopService(intent);
    }
}
