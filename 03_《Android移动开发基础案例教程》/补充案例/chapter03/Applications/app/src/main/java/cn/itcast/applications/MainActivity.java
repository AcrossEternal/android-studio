package cn.itcast.applications;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_firstName;
    private TextView tv_secondPackageName;
    private TextView tv_secondName;
    private TextView tv_firstPackageName;
    private ImageView iv_firstIco, iv_secondIco;
    private PackageManager packageManager;
    private RelativeLayout rl_firstUse;
    private RelativeLayout rl_secondUse;
    private boolean first_isExist;
    private boolean second_isExist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_firstName = (TextView) findViewById(R.id.tv_firstUseHint);
        tv_secondPackageName = (TextView) findViewById(R.id.tv_SecondPackName);
        tv_secondName = (TextView) findViewById(R.id.tv_secondName);
        tv_firstPackageName = (TextView) findViewById(R.id.tv_firstPackageHint);
        iv_firstIco = (ImageView) findViewById(R.id.iv_firstIco);
        iv_secondIco = (ImageView) findViewById(R.id.iv_secondIco);
        rl_firstUse = (RelativeLayout) findViewById(R.id.rl_firstUse);
        rl_secondUse = (RelativeLayout) findViewById(R.id.rl_secondUse);
        rl_firstUse.setOnClickListener(this);
        rl_secondUse.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        packageUtils();
        isExistFlag(first_isExist, rl_firstUse);
        isExistFlag(second_isExist, rl_secondUse);
    }

    private void isExistFlag(boolean flag, RelativeLayout rl) {
        if (!flag) {
            rl.setVisibility(View.GONE);
        } else {
            rl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        first_isExist = false;
        second_isExist = false;
    }

    private void packageUtils() {
        packageManager = this.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager
                .getInstalledPackages(0);
        for (int i = 0; i < packageInfoList.size(); i++) {
            PackageInfo pak = (PackageInfo) packageInfoList.get(i);
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                if (pak.packageName.equals("cn.itcast.demo1")) {
                    setData(pak, tv_firstName, tv_firstPackageName, iv_firstIco);
                    first_isExist = true;
                } else if (pak.packageName.equals("cn.itcast.demo2")) {
                    setData(pak, tv_secondName, tv_secondPackageName, iv_secondIco);
                    second_isExist = true;
                }
            }
        }
    }

    private void setData(PackageInfo pak, TextView name, TextView pakName,
                         ImageView iv) {
        name.setText(packageManager.getApplicationLabel(pak.applicationInfo));
        pakName.setText(pak.packageName);
        Drawable applicationIcon = packageManager.getApplicationIcon(pak.applicationInfo);
        iv.setImageBitmap(drawableToBitmap(applicationIcon));
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_firstUse:
                dialog("cn.itcast.demo1", "cn.itcast.demo1.MainActivity");
                break;
            case R.id.rl_secondUse:
                dialog("cn.itcast.demo2", "cn.itcast.demo2.MainActivity");
                break;
            default:
                break;
        }

    }

    private void dialog(final String name, final String pakName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("选项");
        builder.setPositiveButton("打开应用",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openFile(name, pakName);
                    }
                });
        builder.setNegativeButton("卸载应用",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        uninstall(name);
                    }
                });
        builder.create().show();

    }

    private void openFile(String name, String pakName) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(name, pakName));
            startActivity(intent);
        } catch (Exception e) {
        }
    }

    private void uninstall(String name) {
        Uri packageURI = Uri.parse("package:" + name);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        startActivity(uninstallIntent);
    }


}
