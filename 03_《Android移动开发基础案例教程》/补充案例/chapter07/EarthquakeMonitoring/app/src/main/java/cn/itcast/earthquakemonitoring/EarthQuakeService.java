package cn.itcast.earthquakemonitoring;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EarthQuakeService extends Service {
    public EarthQuakeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    class MyBinder extends Binder {
        public String  getEarthQuakeLevel(){
            return "7级";
        }
        public String getEarthQuakeSource(){
            return "美国";
        }
    }
}
