package cn.itcast.orderedbroadcast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
public class MyBroadcastReceiverThree extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyBroadcastReceiverOne", "自定义的广播接收者Three,接收到了广播事件");
    }
}
