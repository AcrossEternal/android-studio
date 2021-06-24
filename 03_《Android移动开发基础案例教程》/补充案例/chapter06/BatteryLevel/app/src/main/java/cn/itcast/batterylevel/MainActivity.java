package cn.itcast.batterylevel;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView tv_cell, tv_netWork;
    private BatteryReceiver batteryReceiver;
    private ConnectivityReceiver connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_cell = (TextView) findViewById(R.id.tv_cell);
        tv_netWork = (TextView) findViewById(R.id.tv_netWork);
        // 注册广播接受者java代码
        IntentFilter intentFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        batteryReceiver = new BatteryReceiver();
        // 注册receiver
        registerReceiver(batteryReceiver, intentFilter);

        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivity =  new ConnectivityReceiver();
        registerReceiver(connectivity, mFilter);
    }

    /**
     * 广播接受者
     */
    private class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            // 判断它是否是为电量变化的Broadcast Action
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                // 获取当前电量
                int level = intent.getIntExtra("level", 0);
                // 电量的总刻度
                int scale = intent.getIntExtra("scale", 100);
                // 把它转成百分比
                tv_cell.setText("电池电量为" + ((level * 100) / scale) + "%");
            }
        }

    }

    private class ConnectivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            State wifiState = null;
            State mobileState = null;
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .getState();
            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .getState();
            if (wifiState != null && mobileState != null
                    && State.CONNECTED != wifiState
                    && State.CONNECTED == mobileState) {

                Toast.makeText(context, "手机网络连接成功",
                        Toast.LENGTH_SHORT).show();
                tv_netWork.setText("手机网络连接成功");
                // 手机网络连接成功
            } else if (wifiState != null && mobileState != null
                    && State.CONNECTED != wifiState
                    && State.CONNECTED != mobileState) {
                // 手机没有任何的网络
                Toast.makeText(context, "手机没有网络",
                        Toast.LENGTH_SHORT).show();
                tv_netWork.setText("手机没有网络");
            } else if (wifiState != null && State.CONNECTED == wifiState) {
                // 无线网络连接成功
                Toast.makeText(context, "无限网络连接成功",
                        Toast.LENGTH_SHORT).show();
                tv_netWork.setText("无限网络连接成功");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
        unregisterReceiver(connectivity);
    }
}
