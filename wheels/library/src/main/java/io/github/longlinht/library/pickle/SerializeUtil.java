package io.github.longlinht.library.pickle;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import io.github.longlinht.library.BuildConfig;
import io.github.longlinht.library.base.util.DataKeeper;
import io.github.longlinht.library.log.Logger;
import io.github.longlinht.library.utils.PermissionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.github.longlinht.library.network.utils.IOUtils;

/**
 * Created by Tao He on 18-5-3.
 * hetaoof@gmail.com
 */
public class SerializeUtil {

    public static final String TAG = "SerializeUtil";

    public static void clear(String filePath) {

        /*
        if (!PermissionUtils.hasStartPermission()) {
            return;
        }
        */

        if (TextUtils.isEmpty(filePath)) {//位置为空或者对象为null
            return;
        }

        // 从pickle中删除
        Pickles.getDefaultPickle().delete(filePath);

        final File javaSerialFile = new File(filePath);
        if (javaSerialFile.exists() && javaSerialFile.isFile()) {
            final boolean delete = javaSerialFile.delete();
            if (BuildConfig.DEBUG) {
                if (!delete) {
                    Logger.w("删除文件失败 file：%s", javaSerialFile);
                }
            }
        }
    }

    public static Object deserialize(final String filePath, Class<?> clazz) {
        /*
        if (!PermissionUtils.hasStartPermission()) {
            return null;
        }
        */

        Object pickleResult = null;
        try {
            pickleResult = Pickles.getDefaultPickle().get(filePath, clazz);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "deserialize: ", e);
            }
        }
        // 如果已经存在新的序列化发版本了
        if (pickleResult != null) {
            if (BuildConfig.DEBUG) {
                Log.i(TAG, "deserialize: 命中Pickle, key = [" + filePath + "], data = [" + pickleResult + "]");
            }
            return pickleResult;
        }

        return dangerousDeserialize(filePath);
    }

    @Deprecated
    @Nullable
    @VisibleForTesting
    static Object dangerousDeserialize(String filePath) {
        if (!PermissionUtils.hasStartPermission()) {
            return null;
        }

        File objFile = new File(filePath);
        if (BuildConfig.DEBUG) {
            Logger.d("deserialize:cacheFilePath:" + filePath + "exists:" + objFile.exists());
        }

        if (!objFile.exists()) {//不存在就返回
            return null;
        }

        ObjectInputStream in = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(objFile);
            in = new ObjectInputStream(fileInputStream);
            final Object result = in.readObject();

            if (BuildConfig.DEBUG) {
                Log.i(TAG, "deserialize: 从Java序列化中读取 key = [" + filePath + "], data = [" + result + "]");
            }

            // 异步转换为json
            try {
                syncTrans2Json(filePath, result);
            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Log.e(TAG, "dangerousDeserialize: ", e);
                }
            }

            return result;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Logger.d("deserialize " + filePath + " failed!");
            }
            e.printStackTrace();
        } catch (OutOfMemoryError error) {//这里也有可能出现内存问题
            error.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(fileInputStream);
        }
        return null;
    }

    private static void syncTrans2Json(final String filePath, final Object result) {

        Pickles.getDefaultPickle().put(filePath, result);

        if (BuildConfig.DEBUG) {
            if (!Pickles.getDefaultPickle().contains(filePath)) {
                throw new AssertionError("impossible");
            }
        }

    }

    public static synchronized void serialize(final String filePath, final Object obj) {
        /*
        if (!PermissionUtils.hasStartPermission()) {
            return;
        }
        */

        if (TextUtils.isEmpty(filePath) || (null == obj)) {//位置为空或者对象为null
            return;
        }

        // 使用新的序列化方式，就可以了
        try {
            Pickles.getDefaultPickle().put(filePath, obj);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "serialize: ", e);
            }
        }
    }


    @Deprecated
    @VisibleForTesting
    static void dangerousSerialize(String filePath, Object obj) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "dangerousSerialize() called with: cacheFilePath = [" + filePath + "], obj = [" + obj + "]");
        }
        if (!PermissionUtils.hasStartPermission()) {
            return;
        }

        if (TextUtils.isEmpty(filePath) || (null == obj)) {//位置为空或者对象为null
            return;
        }

        File distFile = new File(filePath);
        if (!distFile.getParentFile().exists()) {
            distFile.getParentFile().mkdirs();
        }

        if (!distFile.getParentFile().exists()) {
            return;
        }

        if (distFile.exists()) {
            distFile.delete();
        }
        ObjectOutputStream out = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(obj);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Logger.d("serialize " + filePath + " failed!");
            }
            e.printStackTrace();
            if (distFile.exists()) {
                distFile.delete();
            }
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    private static final String LOGIN_RESULT_SERIALIZE_FILENAME = "loginresult.cache";

    /**
     * 获取用户登录结果信息序列化文件位置
     */
    public static String getLoginResultSerializePath() {
        return DataKeeper.getAppDataPath() + File.separator
                + LOGIN_RESULT_SERIALIZE_FILENAME;
    }
    /**
     * 将用户信息序列化到本地的文件名
     */
    private static final String SERIALIZE_USERMODEL_FILENAME = "usermodel.cache";

    /**
     * 获取用户信息序列化文件位置
     *
     * @param uid 用户id
     */
    public static String getUserSerializePath(String uid) {
        return DataKeeper.getAppDataPath() + File.separator + uid
                + SERIALIZE_USERMODEL_FILENAME;
    }


    private static final String LOGIN_TYPE_FILENAME = "logintype.cache";

    /**
     * 获取用户登录结果信息序列化文件位置
     */
    public static String getLoginTypePath() {
        return DataKeeper.getAppDataPath() + File.separator
                + LOGIN_TYPE_FILENAME;
    }

    /**
     * 用户绑定的手机号存储路径
     */
    private static final String USER_BIND_PHONENUM = "user_bind_phonenum";

    /**
     * 获取用户绑定的手机号文件位置
     *
     * @param uid 用户id
     */
    public static String getUserBindPhonenumPath(int uid) {
        return DataKeeper.getAppDataPath() + File.separator + uid
                + USER_BIND_PHONENUM;
    }
}
