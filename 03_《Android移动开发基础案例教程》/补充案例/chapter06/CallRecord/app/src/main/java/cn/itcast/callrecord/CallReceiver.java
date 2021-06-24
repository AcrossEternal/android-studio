package cn.itcast.callrecord;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import cn.itcast.callrecord.bean.Phone;
import cn.itcast.callrecord.db.PhoneDao;


public class CallReceiver extends BroadcastReceiver {
    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static boolean flag = false;
    /** 区分呼入呼出标记 */
    private static boolean isOuting = false;
    /** 当前操作电话号码 */
    private static String currentNumber = null;
    private String listenerNumber;
    private PhoneDao dao;

    @Override
    public void onReceive(final Context context, Intent intent) {
        dao = new PhoneDao(context);

        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            // 呼出
            isOuting = true;
            String phonenumber = getResultData();// 被呼叫的电话号码
            // 更新电话号码
            if (null != phonenumber && !"".equals(phonenumber)) {
                currentNumber = phonenumber;
            }
        } else {
            if (!flag) {
                TelephonyManager manager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                manager.listen(new PhoneStateListener() {
                    @SuppressLint({ "NewApi", "ShowToast" })
                    @Override
                    public void onCallStateChanged(int state,
                                                   String incomingNumber) {
                        super.onCallStateChanged(state, incomingNumber);
                        flag = true;
                        if (state == TelephonyManager.CALL_STATE_RINGING) {
                            // 来电状态
                            isOuting = false;
                            listenerNumber = incomingNumber;
                            Toast.makeText(context, "来电提醒:	" + listenerNumber,
                                    Toast.LENGTH_SHORT).show();

                        } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                            if (isOuting) {
                                setData(context, currentNumber,"dialed","呼出电话：	");
                            } else {

                                setData(context, listenerNumber,"received","已接来电：	");
                            }
                        }
                        if (lastState == TelephonyManager.CALL_STATE_RINGING
                                && state == TelephonyManager.CALL_STATE_IDLE) {
                            setData(context, incomingNumber,"missed","未接来电：	");
                        }
                        lastState = state;
                    }

                    private void setData(final Context context,
                                         String incomingNumber,String classicy,String hint) {
                        Toast.makeText(context, hint + incomingNumber,
                                Toast.LENGTH_SHORT).show();
                        SimpleDateFormat formatter = new SimpleDateFormat(
                                "yyyy年MM月dd日    HH:mm:ss     ");
                        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
                        String date = formatter.format(curDate);
                        dao.insert(new Phone(incomingNumber, classicy, date));
                    }
                }, PhoneStateListener.LISTEN_CALL_STATE);
            }
        }
    }
}