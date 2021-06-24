package wdj.chapter7.startservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author 13057
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("StartService","onCreate()");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("StartService","onStartCommand()");
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();;
        Log.i("StartService","onDestroy()");
    }
}