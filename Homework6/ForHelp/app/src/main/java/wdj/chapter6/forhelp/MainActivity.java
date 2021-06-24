package wdj.chapter6.forhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

/**
 * @author 13057
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
    public void send(View view){
        Intent intent=new Intent();
        //定义广播的事件类型
        intent.setAction("Help_Stitch");
        //发送广播
        sendBroadcast(intent);
    }
}