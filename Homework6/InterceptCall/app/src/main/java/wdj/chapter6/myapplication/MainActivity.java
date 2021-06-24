package wdj.chapter6.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author 13057
 */
public class MainActivity extends AppCompatActivity {

    private EditText et_ipnumber;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_ipnumber=(EditText) findViewById(R.id.et_ipnumber);
        //创建SharedPreferences对象
        sp = getSharedPreferences("config",MODE_PRIVATE);
    }
    public void click(View view){
        //获取用户输入的拦截号码
        String number=et_ipnumber.getText().toString().trim();
        //创建Editor对象，保存用户输入的拦截号码
        SharedPreferences.Editor editor =sp.edit();
        editor.putString("number",number);
        editor.commit();
        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }
//    public void showRequest(){
//        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.PROCESS_OUTGOING_CALLS)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(MainActivity.this,new String[](Manifest.permission.PROCESS_OUTGOING_CALLS),1);
//        }
//    }
}