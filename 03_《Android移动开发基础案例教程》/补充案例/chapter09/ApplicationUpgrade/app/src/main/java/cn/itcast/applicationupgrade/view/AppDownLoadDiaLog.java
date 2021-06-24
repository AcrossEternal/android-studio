package cn.itcast.applicationupgrade.view;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.itcast.applicationupgrade.R;
import cn.itcast.applicationupgrade.utils.Utils;

public class AppDownLoadDiaLog {
    Context context;
    android.app.AlertDialog alertDialog;
    TextView titleView;
    TextView tv_down, tv_down_size;
    ProgressBar progressBar;
    Button btn_down_canel;

    public AppDownLoadDiaLog(Context context) {
        this.context = context;
        alertDialog = new android.app.AlertDialog.Builder(context).create();
        alertDialog.show();
        // 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.alertdiadownlog);
        tv_down = (TextView) window.findViewById(R.id.tv_down);
        tv_down_size = (TextView) window.findViewById(R.id.tv_down_size);
        btn_down_canel = (Button) window.findViewById(R.id.btn_down_canel);

        progressBar = (ProgressBar) window.findViewById(R.id.bar_down);
        progressBar.setMax(100);
    }

    public void setNegativeButton(String text,
                                  final View.OnClickListener listener) {
        btn_down_canel.setTextSize(Utils.px2dip(context, 24));
        btn_down_canel.setOnClickListener(listener);
    }

    public void setTitle(int resId) {
        titleView.setText(resId);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void setMessage(int resId) {
        tv_down.setText(resId);
    }

    public void setMessage(String message) {
        tv_down.setText(message);
    }

    public void setSize(int resId) {
        tv_down_size.setText(resId);
    }

    public void setSize(String message) {
        tv_down_size.setText(message);
    }

    public void setBar(int resId) {
        progressBar.setProgress(resId);
    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        alertDialog.dismiss();
    }
}
