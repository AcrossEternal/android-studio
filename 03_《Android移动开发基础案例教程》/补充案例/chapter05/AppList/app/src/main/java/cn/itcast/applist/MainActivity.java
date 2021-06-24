package cn.itcast.applist;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<UserAPPInfo> allAppList;
    private MyAdapter adapter;
    private PackageManager pManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAppMessage();
        ListView listView = (ListView) findViewById(R.id.listview);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }

    private void getAppMessage() {
        pManager = getPackageManager();
        List<PackageInfo> packages = getPackageManager()
                .getInstalledPackages(0);
        allAppList = new ArrayList<UserAPPInfo>();
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            UserAPPInfo userInfo = new UserAPPInfo();
            String name = packageInfo.applicationInfo.loadLabel(
                    getPackageManager()).toString();
            userInfo.setName(name);
            userInfo.setPakName(packageInfo.packageName);
            userInfo.setCode(packageInfo.versionName);
            userInfo.setIco(drawableToBitmap(packageInfo.applicationInfo.loadIcon(getPackageManager())));
            allAppList.add(userInfo);
        }
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return allAppList.size();
        }

        @Override
        public Object getItem(int position) {
            return allAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView != null ? convertView : View.inflate(
                    MainActivity.this, R.layout.listview_item, null);
            TextView tv_name = (TextView) item.findViewById(R.id.tv_name);
            TextView tv_pakName = (TextView) item.findViewById(R.id.tv_pakName);
            ImageView iv_ico = (ImageView) item.findViewById(R.id.iv_ico);
            tv_name.setText(allAppList.get(position).getName());
            tv_pakName.setText(allAppList.get(position).getPakName());
            iv_ico.setImageBitmap(allAppList.get(position).getIco());
            return item;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ?
                                Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
