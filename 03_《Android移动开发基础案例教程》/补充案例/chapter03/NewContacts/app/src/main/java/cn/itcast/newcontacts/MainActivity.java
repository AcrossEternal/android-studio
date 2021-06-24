package cn.itcast.newcontacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivHead;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvNumber;
    private String name;
    private String email;
    private byte[] imageByte;
    private Bitmap bitmap;
    private String phone_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();//初始化控件

    }

    public void click(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, 1);
    }

    private void initView() {
        ivHead = (ImageView) findViewById(R.id.iv_head);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvNumber = (TextView) findViewById(R.id.tv_number);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == 1) {
                if (requestCode == 1) {
                    try {
                        name = data.getStringExtra("name");
                        email = data.getStringExtra("email");
                        phone_et = data.getStringExtra("phone");
                        imageByte = data.getByteArrayExtra("image");
                        bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    } catch (Exception e) {
                    }
                    if (name != null) {
                        tvName.setText("姓名	:" + name);
                    }
                    if (email != null) {
                        tvEmail.setText("email	:" + email);
                    }
                    if (phone_et != null) {
                        tvNumber.setText("电话号码	:" + phone_et);
                    }
                    if (bitmap != null) {
                        ivHead.setImageBitmap(bitmap);
                    }
                }
            }
        }
    }


}
