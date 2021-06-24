package cn.itcast.welcomeinterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    private static final String BITMAPADDRESSFORSD = "/sdcard/welcome.jpg";
    private ImageView ll;
    private Bitmap bitmap;

    private Handler handler = new Handler() {
        @SuppressLint("NewApi")
        public void handleMessage(android.os.Message msg) {
            if (msg.what == ERROR) {
                Toast.makeText(MainActivity.this, "显示图片错误", Toast.LENGTH_SHORT).show();
            }
        };
    };
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (ImageView) findViewById(R.id.iv_welcome);
        file = new File(BITMAPADDRESSFORSD);
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(BITMAPADDRESSFORSD);
            ll.setBackground(new BitmapDrawable(bm));
        }
        getServiceImage();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
    /***
     * 获取服务器图片
     */
    private void getServiceImage() {
        new Thread() {
            private HttpURLConnection conn;

            public void run() {
                // 连接服务器 get 请求 获取图片.
                try {
                    URL url = new URL("http://172.17.24.58:8080/a.jpg"); // 创建URL对象
                    conn = (HttpURLConnection) url.openConnection();
                    // 设置请求的方式
                    conn.setRequestMethod("GET");
                    // 设置超时时间
                    conn.setConnectTimeout(5000);
                    // 设置请求头 User-Agent浏览器的版本
                    conn.setRequestProperty(
                            "User-Agent",
                            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; "
                                    + "SV1; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; "
                                    + ".NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; Shuame)");
                    // 得到服务器返回的响应码
                    int code = conn.getResponseCode();
                    // 请求网络成功后返回码是200
                    if (code == 200) {
                        InputStream is = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                        saveBitmapToSD();
                    } else {
                        // 返回码不是200 请求服务器失败
                        Message msg = new Message();
                        msg.what = ERROR;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = ERROR;
                    handler.sendMessage(msg);
                }
            };
        }.start();
    }
    public void saveBitmapToSD() throws IOException {
        File f = new File(BITMAPADDRESSFORSD);
            f.createNewFile();
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
