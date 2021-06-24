package cn.itcast.applicationupgrade;

import java.io.File;

import java.io.FileOutputStream;

import java.io.InputStream;


import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.os.Environment;

import android.os.Handler;

import android.os.Message;

import android.util.Log;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.TextView;

import cn.itcast.applicationupgrade.connector.UpgradeAction;
import cn.itcast.applicationupgrade.utils.Utils;
import cn.itcast.applicationupgrade.view.AppDownLoadDiaLog;


public class MainActivity extends Activity implements UpgradeAction {

    /**
     * 新版本保存在SD卡的名称
     */

    private String appName;

    /**
     * 新版本下载对话框
     */

    private AppDownLoadDiaLog downBar;

    /**
     * 新版本APK总大小
     */

    private int fileLength;

    /**
     * 新版本下载进度
     */

    private int DownedFileLength = 0;

    protected static final int DOWNFNEWVERSION = 101;

    private Thread thread;

    private boolean stopThreadFlag;

    private TextView tv_hint;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        new VersionUpdateTask(this, Utils.SERVICEADDRESS, this).execute();
        tv_hint = (TextView) findViewById(R.id.tv_hint);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_hint.setText("当前版本	:	" + Utils.getAppVersionCode(this));
    }

    protected void downAppFile(final String url) {
        thread = new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();
                    fileLength = (int) entity.getContentLength();
                    FileOutputStream fileOutputStream = null;
                    if (is == null) {
                        throw new RuntimeException("isStream is null");
                    }
                    File file = new File(
                            Environment.getExternalStorageDirectory(), appName);
                    fileOutputStream = new FileOutputStream(file);
                    byte[] buf = new byte[1024];
                    int ch = -1;
                    do {
                        ch = is.read(buf);
                        if (ch <= 0) {
                            break;
                        }
                        fileOutputStream.write(buf, 0, ch);
                        DownedFileLength += ch;

                        Message message1 = new Message();
                        message1.what = DOWNFNEWVERSION;
                        handler.sendMessage(message1);
                    } while (true);
                    is.close();
                    fileOutputStream.close();
                    if (!stopThreadFlag) {
                        Utils.installNewApk(MainActivity.this, appName, downBar);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    private Handler handler = new Handler() {
        private int x;

        public void handleMessage(Message msg) {
            if (!Thread.currentThread().isInterrupted()) {
                switch (msg.what) {
                    case 0:
                        // Log.i("文件长度----------->", progressBar.getMax() + "");
                        break;
                    case DOWNFNEWVERSION:
                        String fileLengthMB = Utils.bytes2kb(fileLength);
                        String DownedFileLengthMB = Utils
                                .bytes2kb(DownedFileLength);
                        x = (int) (DownedFileLength * 100 / fileLength);
                        downBar.setBar(x);
                        downBar.setMessage(x + "%");
                        downBar.setSize(DownedFileLengthMB + "/" + fileLengthMB);
                        break;
                    case 2:
                        Utils.installNewApk(MainActivity.this, appName, downBar);
                        break;
                    default:
                        break;
                }
            }
        }
    };

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void download() {
        appName = "应用升级1" + VersionUpdateTask.newVersionName + ".apk";
        // 点击更新，如果已经下载过最新版本直接安装
        File downLoadApk = new File(Environment.getExternalStorageDirectory(),
                appName);
        if (downLoadApk.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), appName)),
                    "application/vnd.android.package-archive");
            startActivity(intent);
        } else {
            DownedFileLength = 0;
            showProgressBar();
        }

    }

    protected void showProgressBar() {
        downBar = new AppDownLoadDiaLog(MainActivity.this);
        downBar.setNegativeButton("", new OnClickListener() {

            @Override
            public void onClick(View v) {
                downBar.dismiss();
                thread.interrupt();
                stopThreadFlag = true;
                try {
                    File downLoadApk = new File(Environment
                            .getExternalStorageDirectory(), "应用升级1"
                            + VersionUpdateTask.newVersionName + ".apk");
                    if (downLoadApk.exists()) {
                        downLoadApk.delete();
                    }
                } catch (Exception e) {
                    Log.v("VersionUpdate", "", e);
                }
            }
        });
        downAppFile(Utils.APKSERVICEADDRESS);
    }

    @Override
    public void cancel() {

    }
}

