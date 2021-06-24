package cn.itcast.smsreminder;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTitleTV;
    private TextView mContentTV;
    private TextView mSendTimeTV;
    private EditText mContentET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        // 注册内容观察者
        resolver.registerContentObserver(uri, true, new MyObserver(
                new Handler()));
    }
    /** 初始化控件 */
    private void initView() {
        mTitleTV = (TextView) findViewById(R.id.tv_dialog_title);
        mContentTV = (TextView) findViewById(R.id.tv_dialog_content);
        mSendTimeTV = (TextView) findViewById(R.id.tv_dialog_sendTime);
        mContentET = (EditText) findViewById(R.id.et_dialog_sms_content);
        findViewById(R.id.btn_dialog_closed).setOnClickListener(this);
        findViewById(R.id.btn_dialog_send_sms).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_closed:
                // 关闭当前页面
                finish();
                break;
            case R.id.btn_dialog_send_sms:
                // 发送短信
                SmsManager smsManager = SmsManager.getDefault();
                String content = mContentET.getText().toString();
                List<String> texts = smsManager.divideMessage(content);
                for (int i = 0; i < texts.size(); i++) {
                    String text = texts.get(i);
                    smsManager.sendTextMessage(
                            mTitleTV.getText().toString().trim(), null, text, null,
                            null);
                }
                break;
        }
    }
    // 自定义的内容观察者
    private class MyObserver extends ContentObserver {
        public MyObserver(Handler handler) {
            super(handler);
        }
        // 当内容观察者 观察到是数据库的内容变化了 调用这个方法
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Toast.makeText(MainActivity.this, "数据库的内容变化了.", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("content://sms/");
            // 获取ContentResolver对象
            ContentResolver resolver = getContentResolver();
            // 查询变化的数据
            Cursor cursor = resolver.query(uri, new String[] { "address",
                    "date", "type", "body" }, null, null, null);
            // 因为短信是倒序排列 因此获取最新一条就是第一个
            cursor.moveToFirst();
            String address = cursor.getString(0);
            String body = cursor.getString(3);
            long time = cursor.getLong(1);
            // 更改UI界面
            mContentTV.setText("短信内容：\n" + body);
            mTitleTV.setText(address);
            if (DateUtils.isToday(time)) {
                // 日期为今日，就显示时分
                mSendTimeTV.setText(DateFormat.getTimeFormat(MainActivity.this)
                        .format(time));
            } else {
                // 日期不是今日就显示日期
                mSendTimeTV.setText(DateFormat.getDateFormat(MainActivity.this)
                        .format(time));
            }
            cursor.close();
        }
    }
}
