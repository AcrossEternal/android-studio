package cn.itcast.pictureeffects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**原图*/
    private ImageView mOldImgv;
    /**处理后的图片*/
    private ImageView mNewImgv;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    /**初始化控件*/
    private void initView() {
        findViewById(R.id.btn_alpha).setOnClickListener(this);
        findViewById(R.id.btn_amplification).setOnClickListener(this);
        findViewById(R.id.btn_rotate).setOnClickListener(this);
        findViewById(R.id.btn_translation).setOnClickListener(this);
        mOldImgv = (ImageView) findViewById(R.id.imgv_old);
        mNewImgv = (ImageView) findViewById(R.id.imgv_new);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        mOldImgv.setImageBitmap(bitmap);
    }
    /**画出处理后的图形*/
    private void drawBitmap(Matrix matrix) {
        //赶紧临摹一个图片
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),bitmap.getConfig());
        //创建一个画板。构造方法传递空白的图片，按照空白图片的尺寸创建画板。
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();//3.创建一个画笔。
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap, matrix, paint);
        mNewImgv.setImageBitmap(createBitmap);
    }
    @Override
    public void onClick(View v) {
        Matrix matrix = new Matrix();
        switch (v.getId()) {
            case R.id.btn_alpha:        //倾斜
                matrix.setSkew(0.2f, 0.0f);
                drawBitmap(matrix);
                break;
            case R.id.btn_amplification://放大
                matrix.reset();
                matrix.setScale(2.0f, 1.0f);
                drawBitmap(matrix);
                break;
            case R.id.btn_rotate:       //旋转
                matrix.reset();
                matrix.setRotate(20.0f);
                drawBitmap(matrix);
                break;
            case R.id.btn_translation:  //平移
                matrix.reset();
                matrix.setTranslate(30.0f, 20.0f);
                drawBitmap(matrix);
                break;
        }
    }
}
