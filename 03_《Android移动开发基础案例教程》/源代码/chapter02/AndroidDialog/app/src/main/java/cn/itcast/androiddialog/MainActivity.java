package cn.itcast.androiddialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //普通对话框
//        AlertDialog dialog;                           //声明对象
//        dialog = new AlertDialog.Builder(this)        //绑定当前界面窗口
//                .setTitle("Dialog对话框")             //设置标题
//                .setMessage("是否确定退出？")         //设置提示信息
//                .setIcon(R.mipmap.ic_launcher)       //设置图标
//                .setPositiveButton("确定", null)     //添加“确定”按钮
//                .setNegativeButton("取消", null)     //添加“取消”按钮
//                .create();                          //创建对话框
//        dialog.show();                              //显示对话框


        //单选对话框
//        new AlertDialog.Builder(this)               //生成对话框
//                .setTitle("请选择性别")              //设置标题
//                .setIcon(R.mipmap.ic_launcher)      //设置图标
//                .setSingleChoiceItems(new String[]{"男", "女"}, 0,
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        }
//                )
//                .setPositiveButton("确定", null)
//                .show();

        //多选对话框
//        new AlertDialog.Builder(this)
//                .setTitle("请添加兴趣爱好！")
//                .setIcon(R.mipmap.ic_launcher)
//                .setMultiChoiceItems(new String[]{"旅游", "美食", "汽车", "宠物"},
//                        null, null)
//                .setPositiveButton("确定", null)
//                .show();

        //进度条对话框
//        ProgressDialog prodialog;                //声明对话框
//        prodialog = new ProgressDialog(this); //构建对话框
//        prodialog.setTitle("进度条对话框");
//        prodialog.setIcon(R.mipmap.ic_launcher);
//        prodialog.setMessage("正在下载请等候...");
//        prodialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); //设置水平进度条
////        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置圆形进度条
//        prodialog.show();


        //创建Toast
//         Toast.makeText(MainActivity.this, "Hello,Toast", Toast.LENGTH_SHORT).show();

        //自定义对话框
        MyDialog myDialog = new MyDialog(this, "我是自定义的Dialog");
        myDialog.show();

    }

}

