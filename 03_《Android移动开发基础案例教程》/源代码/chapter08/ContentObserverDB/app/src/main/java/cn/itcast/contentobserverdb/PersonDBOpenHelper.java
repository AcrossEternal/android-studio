package cn.itcast.contentobserverdb;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDBOpenHelper extends SQLiteOpenHelper {
    //构造方法，调用此方法新建一个person.db的数据库并返回一个数据库帮助类的对象
    public PersonDBOpenHelper(Context context) {
        super(context, "person.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建该数据库的同时新建一个info表，表中有_id,name这两个字段
        db.execSQL("create table info (_id integer primary key autoincrement, name varchar(20))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
