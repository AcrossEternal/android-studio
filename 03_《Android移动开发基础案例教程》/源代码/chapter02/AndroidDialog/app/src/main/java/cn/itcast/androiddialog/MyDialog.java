package cn.itcast.androiddialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MyDialog extends Dialog {

    private String dialogName;
    private TextView tvMsg;
    private Button btnOK;
    private Button btnCancel;

    public MyDialog(Context context, String dialogName) {
        super(context);
        this.dialogName = dialogName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题
        setContentView(R.layout.my_dialog);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        btnOK = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        tvMsg.setText(dialogName);  //设置自定义对话显示内容
        //为确定按钮设置点击事件
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击确定按钮时的操作
            }
        });
        //为取消按钮设置点击事件
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();//关闭当前对话框
            }
        });
    }
}
