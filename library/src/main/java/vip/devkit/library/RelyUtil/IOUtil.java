package vip.devkit.library.RelyUtil;

import java.io.Closeable;
import java.io.IOException;

/**
 * 作者 by K
 * 时间：on 2017/1/12 11:28
 * 邮箱 by vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class IOUtil {

    private IOUtil() {
        throw new AssertionError();
    }


    /**
     * Close closable object and wrap {@link IOException} with {@link RuntimeException}
     * @param closeable closeable object
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            }
        }
    }

    /**
     * Close closable and hide possible {@link IOException}
     * @param closeable closeable object
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Ignored
            }
        }
    }
}
