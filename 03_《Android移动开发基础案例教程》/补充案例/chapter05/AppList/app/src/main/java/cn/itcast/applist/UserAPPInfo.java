package cn.itcast.applist;

import android.graphics.Bitmap;


public class UserAPPInfo {

    private String name;
    private String pakName;
    private String code;
    private Bitmap ico;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPakName() {
        return pakName;
    }

    public void setPakName(String pakName) {
        this.pakName = pakName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Bitmap getIco() {
        return ico;
    }

    public void setIco(Bitmap ico) {
        this.ico = ico;
    }
}
