package cn.itcast.androidlogcat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity", "Verbose");
        Log.d("MainActivity", "Degug");
        Log.i("MainActivity", "Info");
        Log.w("MainActivity", "Warning");
        Log.e("MainActivity", "Error");

    }
}
