package cn.itcast.callrecord.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.callrecord.bean.Phone;

public class PhoneDao {
    private SQLiteHelper helper;
    public PhoneDao(Context context) {
        helper = new SQLiteHelper(context);
    }
    public void insert(Phone psw) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone", psw.getPhone());
        values.put("classify", psw.getClassify());
        values.put("date", psw.getDate());
        db.insert("phone", null, values);
        db.close();
    }
    public List<Phone> queryAll() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("phone",
                new String[]{"phone", "classify", "date"}, null, null, null,
                null, null);
        List<Phone> list = new ArrayList<Phone>();
        while (c.moveToNext()) {
            String phone = c.getString(c.getColumnIndex("phone"));
            String classify = c.getString(c.getColumnIndex("classify"));
            String date = c.getString(c.getColumnIndex("date"));
            Phone number = new Phone(phone, classify, date);
            list.add(number);
        }
        c.close();
        db.close();
        return list;
    }
}

