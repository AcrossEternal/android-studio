package registercard.homework3.wdj.registercard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et_phone;
    private EditText et_name;
    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_phone=(EditText)findViewById(R.id.et_phone);
        et_name=(EditText)findViewById(R.id.et_name);
        btn_send=(Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                passDate();
            }
        });
    }
    //传递数据方法
    public void passDate(){
        //创建Intent对象
        Intent intent=new Intent(this,SecondActivity.class);
        //将数据存入Intent对象
        intent.putExtra("phone",et_phone.getText().toString().trim());
        intent.putExtra("name",et_name.getText().toString().trim());
        startActivity(intent);
    }
}
