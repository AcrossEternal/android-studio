package cn.itcast.createservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
    @Override
    public void onCreate() {
        super.onCreate();
        // 设置点击Notification就进入主界面

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
			new Intent(this,MainActivity.class), 0);
        // 创建Notification对象
        Notification notification = new Notification.Builder(this)
                .setContentTitle("标题")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("我是在服务中创建的Notification")
                .setDeleteIntent(pendingIntent)
                .build();
        // 让Myservice变成前台服务，并在系统状态栏显示出来
        startForeground(1, notification);
    }
}