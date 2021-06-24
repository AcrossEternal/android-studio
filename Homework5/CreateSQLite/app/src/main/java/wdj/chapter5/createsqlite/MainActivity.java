package wdj.chapter5.createsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context){
        super(context,"itcast.db",null,2);
    }
    //数据库第一次被创建时调用该方法
    @Override
    public void onCreate(SQLiteDatabase db){
        //初始化数据库的表结果，执行一条建表的SQL语句
        db.execSQL("Create Table information(_id integer primary key autoincrement," +
                "name varchar(20),price integer)");
    }
    //档数据库的版本号增加时调用
    @Override
    public void onUpgrade(SQLiteDatabase ab, int oldVersion, int newVersion){

    }
}