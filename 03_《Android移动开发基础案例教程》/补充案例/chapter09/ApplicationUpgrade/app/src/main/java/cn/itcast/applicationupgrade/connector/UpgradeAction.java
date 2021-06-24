package cn.itcast.applicationupgrade.connector;
import android.content.Context;
public interface UpgradeAction {
    public Context getContext();
    public void download();
    public void cancel();
}
