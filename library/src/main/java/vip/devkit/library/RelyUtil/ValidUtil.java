package vip.devkit.library.RelyUtil;

import java.util.Collection;

/**
 * 作者 by K
 * 时间：on 2017/1/12 11:29
 * 邮箱 by vip@devkit.vip
 * <p/>
 * 类用途：提供一些对象有效性校验的方法
 * 最后修改：
 */
public class ValidUtil {

    /**
     * 判断字符串有效性
     * @param src
     * @return
     */
    public static boolean isValid(String src){
        return !(src == null || "".equals(src.trim()));
    }

    /**
     * 判断一个对象是否为空
     * @param obj
     * @return
     */
    public static boolean isValid(Object obj){
        return !(null == obj);
    }
    /**
     * 判断集合的有效性
     * @param col
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isValid(Collection col){
        return !(col == null || col.isEmpty());
    }

    /**
     * 判断数组是否有效
     * @param arr
     * @return
     */
    public static boolean isValid(Object[] arr){
        return !(arr==null || arr.length==0);
    }
}