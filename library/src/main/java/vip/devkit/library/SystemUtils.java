package vip.devkit.library;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StatFs;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 作者 by K
 * 时间：on 2017/1/11 15:26
 * 邮箱 by vip@devkit.vip
 * <p/>
 * 类用途：系统工具
 * 最后修改：
 */
public class SystemUtils {
    Context mContext;

    private SystemUtils() {
        throw new AssertionError();
    }

    /**
     * 获取系统震动
     *
     * @param VTime 震动时间
     */
    public void GetVibrator(int VTime) {
        //震动效果的实现.
        Vibrator vibrator = (Vibrator) mContext.getSystemService(mContext.VIBRATOR_SERVICE);
        vibrator.vibrate(VTime);//表示震动两百毫秒
    }

    /**
     * 获取系统震动
     *
     * @param frequency1 频率
     * @param frequency2 频率
     * @param cycle      周期
     */
    public void GetVibrator(int frequency1, int frequency2, int cycle) {
        //震动效果的实现.
        Vibrator vibrator = (Vibrator) mContext.getSystemService(mContext.VIBRATOR_SERVICE);
        //如果是下面这种前面参数表示震动频率,一百,两百,一百,后面的参数表示震动周期.也就是重复多少次
        vibrator.vibrate(new long[]{frequency1, frequency2}, cycle);
    }

    /**
     * 获得所有开机启动的应用名
     *
     * @return
     */
    public List<String> getStartupPackname() {
        //查询手机里面的应用程序的意图过滤器, 看哪个应用程序里面有 android.intent.action.BOOT_COMPLETED,有这个就表示会开机启动.
        List<String> packnames = new ArrayList<String>();
        PackageManager pm = mContext.getPackageManager();
        Intent intent = new Intent("android.intent.action.BOOT_COMPLETED");
        //获得
        List<ResolveInfo> infos = pm.queryBroadcastReceivers(intent, PackageManager.GET_INTENT_FILTERS);
        for (ResolveInfo info : infos) {
            String receivername = info.activityInfo.name;//获得广播接收者的名字
            String packname = info.activityInfo.packageName;//包名
            packnames.add(packname);
        }
        return packnames;
    }

    /**
     * 获取手机自带可用内存rom.
     *
     * @return
     */
    public String getAvailRom() {
        File path = Environment.getDataDirectory();
        //StatFs用于获取一个
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(mContext, blockSize * availableBlocks);
    }


    /**
     * 获取手机剩余动态内存ram大小
     *
     * @return
     */
    public String getInternalAvailSize() {
        StatFs mDataFileStats = new StatFs("/data");
        long freeStorage = (long) mDataFileStats.getAvailableBlocks()
                * mDataFileStats.getBlockSize();
        return Formatter.formatFileSize(mContext, freeStorage);
    }

    /**
     * Sdcard的可用空间
     *
     * @return
     */
    public String getAvailSD() {
        //判断是否有插入存储卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();//获取sd路径
            // StatFs:检索有关整体上的一个文件系统的空间信息
            StatFs stat = new StatFs(path.getPath());
            // 一个文件系统的块大小，以字节为单位。
            long blockSize = stat.getBlockSize();
            // SD卡中可用的块数
            long availableBlocks = stat.getAvailableBlocks();
            return Formatter.formatFileSize(mContext, blockSize * availableBlocks);
        } else {
            return null;
        }
    }


    /**
     * Sdcard的总空间
     *
     * @return
     */
    public String getALLAvailSdSize() {
        //判断是否有插入存储卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();
            // StatFs:检索有关整体上的一个文件系统的空间信息
            StatFs stat = new StatFs(path.getPath());
            // 一个文件系统的块大小，以字节为单位。
            long blockSize = stat.getBlockCount();
            // SD卡中可用的块数
            long availableBlocks = stat.getAvailableBlocks();
            return Formatter.formatFileSize(mContext, blockSize * availableBlocks);
        } else {
            return null;
        }
    }

    /**
     * 获得手机sim卡序列号
     *
     * @return
     */
    public String getSimNum() {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(mContext.TELEPHONY_SERVICE);
        String simnum = tm.getSimSerialNumber();
        return simnum;
    }


    /**
     * 获取MAC地址
     *
     * @return
     */
    private String getLocalMacAddress() {
        WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * get recommend default thread pool size
     *
     * @return if 2 * availableProcessors + 1 less than 8, return it, else return 8;
     * @see {@link #getDefaultThreadPoolSize(int)} max is 8
     */
    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    /**
     * get recommend default thread pool size
     *
     * @param max
     * @return if 2 * availableProcessors + 1 less than max, return it, else return max;
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }

    /**
     * 调用系统发送短信
     *
     * @param cxt
     * @param smsBody
     */
    public static void sendSMS(Context cxt, String smsBody) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        cxt.startActivity(intent);
    }

    /**
     * 跳转到拨号
     *
     * @param context
     * @param phoneNumber
     */
    public static void forwardToDial(Context context, String phoneNumber) {
        if (context != null && !TextUtils.isEmpty(phoneNumber)) {
            context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
        }
    }

    /**
     * 直接呼叫号码
     *
     * @param context
     * @param phoneNumber
     */
    public static void callPhone(Context context, String phoneNumber) {
        if (context != null && !TextUtils.isEmpty(phoneNumber)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
        }
    }

    /**
     * 发邮件
     *
     * @param mContext
     * @param mailID
     */
    public static void sendMail(Context mContext, String mailID) {
        Uri uri = Uri.parse("mailto:" + mailID);
        mContext.startActivity(new Intent(Intent.ACTION_SENDTO, uri));
    }

    /**
     * 隐藏系统键盘
     *
     * @param aty
     */
    public static void hideKeyBoard(Activity aty) {
        ((InputMethodManager) aty.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(aty.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 判断当前应用程序是否后台运行
     *
     * @param context
     * @return
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
            }
        }
        return false;
    }

    /**
     * 判断手机是否处理睡眠
     *
     * @param context
     * @return
     */
    public static boolean isSleeping(Context context) {
        KeyguardManager kgMgr = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return kgMgr.inKeyguardRestrictedInputMode();
    }


    static final String suSearchPaths[] = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};

    /**
     * 是否Root
     *
     * @return
     */
    public static boolean isRooted() {
        File file;
        boolean flag1 = false;
        for (String suSearchPath : suSearchPaths) {
            file = new File(suSearchPath + "su");
            if (file.isFile() && file.exists()) {
                flag1 = true;
                break;
            }
        }
        return flag1;
    }

    /**
     * 当前设备是否是模拟器
     *
     * @return
     */
    public static boolean isRunningOnEmulator() {
        return Build.BRAND.contains("generic")
                || Build.DEVICE.contains("generic")
                || Build.PRODUCT.contains("sdk")
                || Build.HARDWARE.contains("goldfish")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("vbox86p")
                || Build.DEVICE.contains("vbox86p")
                || Build.HARDWARE.contains("vbox86");
    }

    /**
     * 返回Home
     *
     * @param context
     */
    public static void goHome(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }

    /**
     * 获取设备可用空间
     *
     * @param cxt
     * @return
     */
    public static int getDeviceUsableMemory(Context cxt) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return (int) (mi.availMem / (1024 * 1024));
    }


    /**
     * 清理后台进程和服务
     *
     * @param cxt
     * @return
     */
    public static int gc(Context cxt) {
        //long i = getDeviceUsableMemory(cxt);
        int count = 0;
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = am.getRunningServices(100);
        if (serviceList != null)
            for (ActivityManager.RunningServiceInfo service : serviceList) {
                if (service.pid == android.os.Process.myPid())
                    continue;
                try {
                    android.os.Process.killProcess(service.pid);
                    count++;
                } catch (Exception e) {
                    e.getStackTrace();
                    continue;
                }
            }

        List<ActivityManager.RunningAppProcessInfo> processList = am.getRunningAppProcesses();
        if (processList != null)
            for (ActivityManager.RunningAppProcessInfo process : processList) {
                if (process.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    String[] pkgList = process.pkgList;
                    for (String pkgName : pkgList) {
                        try {
                            am.killBackgroundProcesses(pkgName);
                            count++;
                        } catch (Exception e) {
                            e.getStackTrace();
                            continue;
                        }
                    }
                }
            }
        return count;
    }


    /**
     * 创建桌面快捷方式
     *
     * @param cxt
     * @param shortCutName
     * @param icon
     * @param cls
     */
    public static void createDeskShortCut(Context cxt, String shortCutName, int icon, Class<?> cls) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        Parcelable ico = Intent.ShortcutIconResource.fromContext(cxt.getApplicationContext(), icon);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, ico);
        Intent intent = new Intent(cxt, cls);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        cxt.sendBroadcast(shortcutIntent);
    }

    /**
     * 创建快捷方式
     *
     * @param ctx
     * @param shortCutName
     * @param iconId
     * @param presentIntent
     */
    public static void createShortcut(Context ctx, String shortCutName, int iconId, Intent presentIntent) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(ctx, iconId));
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, presentIntent);
        ctx.sendBroadcast(shortcutIntent);
    }

    /**
     * 分享文本
     *
     * @param ctx
     * @param title
     * @param text
     */
    public static void shareText(Context ctx, String title, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        ctx.startActivity(Intent.createChooser(intent, title));
       /* List<ResolveInfo> ris = getShareTargets(ctx);
        if (ris != null && ris.size() > 0) {
            ctx.startActivity(Intent.createChooser(intent, title));
        }*/
    }

    /**
     * 分享文件(此方法是调用FileUtils.shareFile中的方式)
     *
     * @param context
     * @param title
     * @param filePath
     */
    public static void shareFile(Context context, String title, String filePath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.parse("file://" + filePath);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, title));
    }


    /**
     * 获取可接受分享的应用
     *
     * @param ctx
     * @return
     */
    public static List<ResolveInfo> getShareTargets(Context ctx) {
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        PackageManager pm = ctx.getPackageManager();
        return pm.queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
    }

    /**
     * 获取当前系统的语言
     *
     * @return
     */
    public static String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * @param ctx
     * @return
     */
    public static String getLanguage(Context ctx) {
        if (ctx != null) {
            return ctx.getResources().getConfiguration().locale.getLanguage();
        }
        return null;
    }

    /**
     * 是否打开GPS
     * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     *
     * @param context
     * @return
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 显示软键盘
     *
     * @param context
     * @param editText
     */
    public static void showSoftInputMethod(Context context, EditText editText) {
        if (context != null && editText != null) {
            editText.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }


    /**
     * 关闭软键盘
     *
     * @param context
     * @param editText
     */
    public static void closeSoftInputMethod(Context context, EditText editText) {
        if (context != null && editText != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     *
     * @param context
     */
    public static void showSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 关闭软键盘
     *
     * @param context
     */
    public static void closeSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}