package cn.itcast.note;

import android.net.Uri;
public class Uris {
    public  static final Uri AUTHORITY
            =Uri.parse("content://cn.itcast.note");
    public  static final Uri QUERY
            =Uri.parse("content://cn.itcast.note/note/query");
    public  static final Uri DELETE
            =Uri.parse("content://cn.itcast.note/note/delete");
    public  static final Uri INSERT
            =Uri.parse("content://cn.itcast.note/note/insert");
    public  static final Uri UPDATE
            =Uri.parse("content://cn.itcast.note/note/update");
}

