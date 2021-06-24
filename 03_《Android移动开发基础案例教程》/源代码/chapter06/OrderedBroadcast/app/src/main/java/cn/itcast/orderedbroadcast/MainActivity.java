package cn.itcast.orderedbroadcast;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void send(View view){
        Intent intent = new Intent();
        // 定义广播的事件类型
        intent.setAction("Intercept_Stitch");
        // 发送广播
        sendOrderedBroadcast(intent,null);
    }
}
