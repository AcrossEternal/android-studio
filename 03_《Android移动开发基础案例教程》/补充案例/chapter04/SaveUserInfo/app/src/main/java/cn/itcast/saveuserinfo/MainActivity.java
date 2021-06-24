package cn.itcast.saveuserinfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etComp;
    private EditText etPhone;
    private EditText etEmail;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.et_name);
        etComp = (EditText) findViewById(R.id.et_company);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etEmail = (EditText) findViewById(R.id.et_email);
        sp = getSharedPreferences("data", Context.MODE_PRIVATE);
        findViewById(R.id.btn_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                if (!(TextUtils.isEmpty(etName.getText().toString().trim()))) {
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("name", etName.getText().toString().trim());
                    edit.putString("company", etComp.getText().toString().trim());
                    edit.putString("phone", etPhone.getText().toString().trim());
                    edit.putString("email", etEmail.getText().toString().trim());
                    edit.commit();
                    startActivity(new Intent(this, ShowUserInfoActivity.class));
                } else {
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
