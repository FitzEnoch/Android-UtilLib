package vip.devkit.library;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation
        .Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者 by K
 * 时间：on 2017/1/11 16:20
 * 邮箱 by vip@devkit.vip
 * <p/>
 * 类用途： 公共类
 * 最后修改：
 */
public class CommonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 判断网络是否可用
     *
     * @return
     */
    public boolean getNetWorkStatus() {
        boolean netSataus = false;
        ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null) {//如果连接对象不为空,表示网络可用.
            netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
        }
        //如果没有网络,弹出对话框询问用户是否要设置.
        if (!netSataus) {
            AlertDialog.Builder b = new AlertDialog.Builder(this).setTitle("没有可用的网络")
                    .setMessage("是否对网络进行设置？");
            b.setPositiveButton("是", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent mIntent = new Intent("/");
                    //进入设置界面时进行设置.
                    ComponentName comp = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    mIntent.setComponent(comp);
                    mIntent.setAction("android.intent.action.VIEW");
                    startActivityForResult(mIntent, 0);
                }
            }).setNeutralButton("否", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            }).show();
        }
        return netSataus;
    }
}