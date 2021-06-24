package cn.itcast.applicationupgrade.view;


import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.itcast.applicationupgrade.R;

public class VersionUpdateHintDialog {
    Context context;
    android.app.AlertDialog alertDialog;
    TextView tv_title, tv_title_msg;
    TextView messageView;
    LinearLayout ll_button;
    private Button btn_confirm, btn_cancel;
    public VersionUpdateHintDialog(Context context) {
        this.context = context;
        alertDialog = new android.app.AlertDialog.Builder(context).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.alertdialog);
        tv_title = (TextView) window.findViewById(R.id.tv_title);
        messageView = (TextView) window.findViewById(R.id.tv_message);
        ll_button = (LinearLayout) window.findViewById(R.id.ll_buttonLayout);
        btn_confirm = (Button) window.findViewById(R.id.btn_confirm);
        btn_cancel = (Button) window.findViewById(R.id.btn_cancel);
        tv_title_msg = (TextView) window.findViewById(R.id.tv_title_msg);
    }
    public void setTitle(int resId) {
        tv_title.setText(resId);
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setMessage(int resId) {
        messageView.setText(resId);
    }

    public void setMessage(String message) {
        messageView.setText(message);
    }

    public void setPositiveButton(String text,
                                  final View.OnClickListener listener) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn_confirm.setLayoutParams(params);
        btn_confirm.setOnClickListener(listener);
    }

    public void setNegativeButton(String text,
                                  final View.OnClickListener listener) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn_cancel.setLayoutParams(params);
        btn_cancel.setOnClickListener(listener);
        if (ll_button.getChildCount() > 0) {
            params.setMargins(20, 0, 0, 0);
            btn_cancel.setLayoutParams(params);
        } else {
            btn_cancel.setLayoutParams(params);
        }

    }

    public void dismiss() {
        alertDialog.dismiss();
    }
}
