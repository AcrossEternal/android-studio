package cn.itcast.userregist;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    private EditText et_password;
    private Button btn_send;
    private EditText et_name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_send = (Button) findViewById(R.id.btn_send);
        //点击开始游戏按钮进行数据传递
        btn_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                passDate();
            }
        });
    }
    //传递数据
    public void passDate() {
        //创建Intent对象,启动Activity02
        Intent intent = new Intent(this, ShowActivity.class);
        //将数据存入Intent对象
        intent.putExtra("name", et_name.getText().toString().trim());
        intent.putExtra("password", et_password.getText().toString().trim());
        startActivity(intent);
    }
}
