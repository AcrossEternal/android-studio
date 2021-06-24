package cn.itcast.earthquakemonitoring;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private EarthQuakeService.MyBinder binder;
    private MyConn conn;
    private LinearLayout mInfoLL;
    private TextView mLevelTV;
    private TextView mSourceTV;

    class MyConn implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (EarthQuakeService.MyBinder)service;
            Toast.makeText(getApplicationContext(), "地震监测服务绑定成功",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInfoLL = (LinearLayout) findViewById(R.id.ll_earthquake_info);
        findViewById(R.id.btn_bindService).setOnClickListener(this);
        findViewById(R.id.btn_getinfo).setOnClickListener(this);
        mLevelTV = (TextView) findViewById(R.id.tv_earthqueake_level);
        mSourceTV = (TextView) findViewById(R.id.tv_earthqueake_source);
        conn = new MyConn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bindService:
                //绑定服务
                bindService(new Intent(this,EarthQuakeService.class),
                        conn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_getinfo:
                if(binder !=null){
                    mInfoLL.setVisibility(View.VISIBLE);
                    String earthQuakeLevel = binder.getEarthQuakeLevel();
                    String earthQuakeSource = binder.getEarthQuakeSource();
                    mLevelTV.setText(earthQuakeLevel);
                    mSourceTV.setText(earthQuakeSource);
                }else{
                    Toast.makeText(getApplicationContext(), "请先绑定地震监测服务",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
