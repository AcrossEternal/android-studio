package cn.itcast.scratchcard;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap alterbitmap;
    private double nX, nY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imgv);
        //从资源文件中解析一张bitmap
        Bitmap bitmap =
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.scratch_card);
        alterbitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), bitmap.getConfig());
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        nX = (double) bitmap.getWidth() / dm.widthPixels;
        nY = (double) bitmap.getHeight() / dm.heightPixels;
        //创建一个canvas对象
        Canvas canvas = new Canvas(alterbitmap);
        //创建画笔对象
        Paint paint = new Paint();
        //为画笔设置颜色
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        //创建Matrix对象
        Matrix matrix = new Matrix();
        //在alterBitmap上画图
        canvas.drawBitmap(bitmap, matrix, paint);
        //为ImageView设置触摸监听
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    for (int i = -100; i < 100; i++) {
                        for (int j = -100; j < 100; j++) {
                            //将区域类的像素点设为透明像素
                            if (Math.sqrt((i * i) + (j * j)) <= 100) {
                                alterbitmap.setPixel((int) (x * nX) + i,
                                        (int) (y * nY+90) + j, Color.TRANSPARENT);
                            }
                        }
                    }
                    imageView.setImageBitmap(alterbitmap);

                } catch (Exception e) {
                    //加try{}catch(){}放置用户触摸图片以外的地方而异常退出
                    e.printStackTrace();
                }
                //销毁掉该触摸事件
                return true;
            }
        });
    }
}
