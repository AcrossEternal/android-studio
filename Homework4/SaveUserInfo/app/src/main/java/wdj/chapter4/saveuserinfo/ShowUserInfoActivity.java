package wdj.chapter4.saveuserinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ShowUserInfoActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvComp;
    private TextView tvEmail;
    private TextView tvPhone;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);
        initView();
        tvName.setText(sp.getString("name", "name"));
        tvComp.setText(sp.getString("company", "company"));
        tvEmail.setText(sp.getString("email", "email"));
        tvPhone.setText(sp.getString("phone", "phone"));
    }

    private void initView() {
        tvName = (TextView) findViewById(R.id.tv_name);
        tvComp = (TextView) findViewById(R.id.tv_company);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        sp = getSharedPreferences("data", Context.MODE_PRIVATE);
    }

}