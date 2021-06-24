package com.example.a13057.a41_saveqq;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FileSaveQQ {
    //保存QQ账号和登录密码 到data.txt
    public static boolean saveUserInfo(Context context, String number,String password){
        try{
            //1.通过上下文获取文件输出流
            FileOutputStream fos=context.openFileOutput("data.txt",Context.MODE_PRIVATE);
            //2.把数据写到文件中
            fos.write((number+":"+password).getBytes());
            fos.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //从data.txt文件中读取QQ账号和密码
    public static Map<String,String> getUserInfo(Context context){
        String content="";
        try{
            FileInputStream fis=context.openFileInput("data.txt");
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            content =new String(buffer);
            Map<String,String> userMap=new HashMap<String, String>();
            String[] infos=content.split(":");
            userMap.put("number",infos[0]);
            userMap.put("password",infos[1]);
            fis.close();
            return userMap;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
