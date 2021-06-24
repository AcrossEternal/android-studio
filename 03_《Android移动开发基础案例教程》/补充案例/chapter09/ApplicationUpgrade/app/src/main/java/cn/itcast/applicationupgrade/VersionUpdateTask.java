package cn.itcast.applicationupgrade;

import java.io.File;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;

import com.loopj.android.http.HttpGet;

import cn.itcast.applicationupgrade.connector.UpgradeAction;
import cn.itcast.applicationupgrade.utils.Utils;
import cn.itcast.applicationupgrade.view.VersionUpdateHintDialog;

public class VersionUpdateTask extends AsyncTask<Void, Void, String> {
    private final UpgradeAction action;
    private Context context;
    private String versionUrlAddress;
    private String versionData;
    public static String newVersionName;

    public VersionUpdateTask(UpgradeAction action, String url, Context context) {
        super();
        this.versionUrlAddress = url;
        this.action = action;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            HttpGet httpGet = new HttpGet(versionUrlAddress);

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpGet);
            client.getParams().setParameter(
                    CoreConnectionPNames.CONNECTION_TIMEOUT, 800);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                    800);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                versionData = EntityUtils.toString(response.getEntity(), "GBK");
                return versionData;
            }

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(String jo) {
        if (!Utils.isNetworkConnected(context)) {
            return;
        }
        String[] split = jo.split(",");
        newVersionName = split[0];
        String replace = split[1].replace(";", "\n");
        String clientVersionName = Utils.getAppVersionCode(context);

        float netFloat = Float.parseFloat(newVersionName);
        float parseFloata = Float.parseFloat(clientVersionName);


        System.out.println("netInt = "+netFloat);
        System.out.println("parseInt = "+parseFloata);

        if (netFloat > parseFloata) {
            final VersionUpdateHintDialog ad = new VersionUpdateHintDialog(
                    context);
            ad.setTitle("是否下载新版本？");
            ad.setMessage(replace);
            ad.setPositiveButton("", new OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.download();
                    ad.dismiss();
                }
            });
            ad.setNegativeButton("", new OnClickListener() {

                @Override
                public void onClick(View v) {
                    action.cancel();
                    ad.dismiss();
                }
            });
        } else {
            File downLoadApk = new File(
                    Environment.getExternalStorageDirectory(), Utils.APKNAME
                    + VersionUpdateTask.newVersionName + ".apk");
            if (downLoadApk.exists()) {
                downLoadApk.delete();
            }
        }
    }
}
