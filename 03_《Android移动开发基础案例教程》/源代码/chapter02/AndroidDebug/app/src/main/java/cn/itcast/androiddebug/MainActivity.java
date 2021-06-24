package cn.itcast.androiddebug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        editText = (EditText) findViewById(R.id.et);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(editText.getText().toString());
                int sum = 0;
                for (int i = 0; i <= n; i++) {
                    //第二个参数强转成Int类型
                    Log.i("i =", Integer.toString(i));
                    sum += i;
                    Log.i("sum = ", Integer.toString(sum));
                }
                textView.setText("从0累加到" + n + "，总和是：" + sum);
            }
        });
    }
}
