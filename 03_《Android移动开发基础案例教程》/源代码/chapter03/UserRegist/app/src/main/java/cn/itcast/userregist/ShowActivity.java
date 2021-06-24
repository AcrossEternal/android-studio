package cn.itcast.userregist;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    private ProgressBar mProgressBar1;
    private ProgressBar mProgressBar2;
    private ProgressBar mProgressBar3;
    private TextView mLifeTV;
    private TextView mAttackTV;
    private TextView mSpeedTV;
    private TextView tv_name;
    private TextView tv_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //获取Intent对象
        Intent intent = getIntent();
        //取出key对应的value值
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_name.setText("用户名：" + name);
        tv_password.setText("密    码：" + password);
        mLifeTV = (TextView) findViewById(R.id.tv_life_progress);
        mAttackTV = (TextView) findViewById(R.id.tv_attack_progress);
        mSpeedTV = (TextView) findViewById(R.id.tv_speed_progress);
        initProgress();                    //初始化进度条
    }
    private void initProgress() {
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        mProgressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        mProgressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        mProgressBar1.setMax(1000);        //设置最大值1000
        mProgressBar2.setMax(1000);
        mProgressBar3.setMax(1000);
    }
    // 开启新的activity并获取他的返回值
    public void click(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivityForResult(intent, 1); // 返回请求结果,请求码为1
    }
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            // 判断结果码是否等于1,等于1为宝宝添加装备
            if (resultCode == 1) {
                if (requestCode == 1) {
                    ItemInfo info =
                            (ItemInfo) data.getSerializableExtra("equipment");
                    //更新ProgressBar的值
                    updateProgress(info);
                }
            }
        }
    }
    //更新ProgressBar的值
    private void updateProgress(ItemInfo info) {
        int progress1 = mProgressBar1.getProgress();
        int progress2 = mProgressBar2.getProgress();
        int progress3 = mProgressBar3.getProgress();
        mProgressBar1.setProgress(progress1 + info.getLife());
        mProgressBar2.setProgress(progress2 + info.getAcctack());
        mProgressBar3.setProgress(progress3 + info.getSpeed());
        mLifeTV.setText(mProgressBar1.getProgress() + "");
        mAttackTV.setText(mProgressBar2.getProgress() + "");
        mSpeedTV.setText(mProgressBar3.getProgress() + "");
    }
}
