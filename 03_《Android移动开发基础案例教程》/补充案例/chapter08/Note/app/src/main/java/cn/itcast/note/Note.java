package cn.itcast.note;

import java.io.Serializable;

public class Note implements Serializable {
    public int id;
    public String title;
    public String content;
    public String date;
    @Override
    public String toString() {
        return title;
    }
}

