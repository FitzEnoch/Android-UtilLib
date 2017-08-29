package vip.devkit.library;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;

/**
 * 作者 by K
 * 时间：on 2017/1/11 14:50
 * 邮箱 by vip@devkit.vip
 * <p/>
 * 类用途： 验证手机格式  获取当前系统时间戳 获取当前wifiSSID
 * 最后修改：
 */
public class CommonUtil {


    /**
     * 获取当前系统时间戳
     * @return
     */
    public static String getTime() {
        long time = System.currentTimeMillis();//获取系统时间的时间戳
        String str = String.valueOf(time);
        return str;
    }

    /**
     * 获取当前wifiSSID
     *
     * @param context
     * @return
     */
    public static String getWifiSSID(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                String ssid = connectionInfo.getSSID();
                if (Build.VERSION.SDK_INT >= 17 && ssid.startsWith("\"") && ssid.endsWith("\""))
                    ssid = ssid.replaceAll("^\"|\"$", "");
                return ssid;
            }
        }
        return "Unknow";
    }

    /**
     * 验证手机格式
     *
     移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     联通：130、131、132、152、155、156、185、186
     电信：133、153、180、189、（1349卫通）
     其他：170、171、144、151、
     * @param number
     * @return
     */
    public static boolean isMobile(String number) {
        /**
         移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         联通：130、131、132、152、155、156、185、186
         电信：133、153、180、189、（1349卫通）
         */
        String num = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }
}

