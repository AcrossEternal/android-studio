package cn.itcast.registuserinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    // 展示用户名和电话
    private TextView tv_showName, tv_showPhone;
    // 修改密码所输入的值
    private String name, oldPsw, newPsw;
    // 修改密码输入框
    private EditText et_changeUsername, et_changeOldPsw, et_changeNewPsw;
    private ListView listview;
    private List<User> allUser;
    private ListViewAdapter adapter;
    private Button btn_deleteAll, btn_findAllUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        tv_showName = (TextView) findViewById(R.id.tv_phone);
        tv_showPhone = (TextView) findViewById(R.id.tv_nickname);
        tv_showName.setText("电话号码：" + phone);
        tv_showPhone.setText("昵称:" + name + "（火星）");
        btn_deleteAll = (Button) findViewById(R.id.btn_deleteAllUser);
        btn_findAllUser = (Button) findViewById(R.id.btn_findAllUser);
    }

    // 修改密码对话框
    public void changepsw(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        view = View.inflate(this, R.layout.changepsw_dialog, null);
        builder.setView(view);
        et_changeUsername = (EditText) view.findViewById(R.id.et_username);
        et_changeOldPsw = (EditText) view.findViewById(R.id.et_psw);
        et_changeNewPsw = (EditText) view.findViewById(R.id.et_newpsw);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = et_changeUsername.getText().toString().trim();
                oldPsw = et_changeOldPsw.getText().toString().trim();
                newPsw = et_changeNewPsw.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(oldPsw)
                        || TextUtils.isEmpty(newPsw)) {
                    Toast.makeText(SecondActivity.this, "选项不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String password = UserInfoManager.getInstance(
                        SecondActivity.this).findData(name,
                        UserInfoManager.PASSWORD);
                String phone = UserInfoManager.getInstance(SecondActivity.this)
                        .findData(name, UserInfoManager.PHONE);
                if (password.equals(oldPsw)) {
                    User user = new User(name, newPsw, phone);
                    UserInfoManager.getInstance(SecondActivity.this)
                            .updateUser(user);
                    Toast.makeText(SecondActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    UserInfoManager.dialogState(dialog, true);
                } else {
                    Toast.makeText(SecondActivity.this, "用戶名或密码不正确", Toast.LENGTH_SHORT).show();
                    UserInfoManager.dialogState(dialog, false);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserInfoManager.dialogState(dialog, true);
            }
        });
        builder.create().show();
    }

    public void findAllUser(View view) {
        allUser = UserInfoManager.getInstance(this).queryAll();
        listview = (ListView) findViewById(R.id.listview);
        listview.setVisibility(View.VISIBLE);
        adapter = new ListViewAdapter(this, allUser);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(adapter);
        btn_findAllUser.setVisibility(View.GONE);
        btn_deleteAll.setVisibility(View.VISIBLE);
        adapter.setOnClickListener(new ListViewAdapter.onClickListener() {
            @Override
            public void onClick() {
                if (allUser.size() == 0) {
                    listview.setVisibility(View.GONE);
                    btn_deleteAll.setVisibility(View.GONE);
                }
            }
        });
    }

    public void deleteAll(View view) {
        UserInfoManager.getInstance(this).deleteAll();
        allUser.clear();
        adapter.notifyDataSetChanged();
        btn_deleteAll.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
    }

}
