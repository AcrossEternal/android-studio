package cn.itcast.callrecord.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context) {
        super(context, "CallRecords.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table phone (id integer primary key autoincrement," +
                "phone varchar(20)," +
                "classify varchar(20)," +
                "date varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
