package io.github.longlinht.library.pickle;

import android.os.Process;

import androidx.annotation.NonNull;

import io.github.longlinht.library.concurrent.AndroidExecutors;
import io.github.longlinht.library.concurrent.TaoThreadFactory;
import io.github.longlinht.library.guava.Supplier;
import io.github.longlinht.library.guava.Suppliers;
import io.github.longlinht.library.log.Logger;

import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import rx.Scheduler;
import rx.Single;
import rx.schedulers.Schedulers;

/**
 * 键值存储类
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 *
 */
public class Pickle {

    private final Converter converter;
    private final Encryption encryption;
    private final Storage storage;

    Pickle(Converter converter, Encryption encryption, Storage storage) {
        this.converter = converter;
        this.encryption = encryption;
        this.storage = storage;
    }

    /**
     * Saves any type including any collection, primitive values or custom objects
     *
     * @param key   is required to differentiate the given data
     * @param value is the data that is going to be encrypted and persisted
     * @return true if the operation is successful. Any failure in any step will return false
     */
    public <T> boolean put(@NonNull String key, @NonNull T value) {

        // 1. trans2json
        final String json;
        try {
            json = converter.toJson(value);
        } catch (Exception e) {
            // json 异常不应该发生，数据结构问题
            Logger.e(e, "[Pickle->put] 序列化为json时失败, key: %s, value: %s", key, value);
            return false;
        }

        // 2. encrypt
        final String encrypt;
        try {
            encrypt = encryption.encrypt(key, json);
        } catch (Exception e) {

            Logger.e(e, "[Pickle->put] 加密时失败 key: %s, value: %s", key, value);
            return false;
        }

        // 3. store
        try {
            return storage.put(key, encrypt);
        } catch (Exception e) {
            Logger.e(e, "[Pickle->put] 存贮过程中发生异常 key: %s, value: %s", key, value);
        }

        return false;
    }

    /**
     * Gets the original data along with original type by the given key.
     * This is not guaranteed operation since Hawk uses serialization. Any change in in the requested
     * data type might affect the result. It's guaranteed to return primitive types and String type
     *
     * @param key  is used to get the persisted data
     * @param type the desired java types for persisted data
     * @return the original object
     */
    public <T> T get(@NonNull String key, @NonNull Type type) {

        // 1. read from store
        final String json;
        try {
            json = storage.get(key);
        } catch (Exception e) {
            Logger.e(e, "[Pickle->get] 从storage获取时发生异常, key: %s, type: %s", key, type);
            return null;
        }

        if (isEmpty(json)) {
            return null;
        }

        // 2. 解密
        final String decrypt;
        try {
            decrypt = encryption.decrypt(key, json);
        } catch (Exception e) {
            Logger.e(e, "[Pickle->get] 解密时失败 key: %s, type: %s", key, type);
            return null;
        }

        // 3. json 转换为对象
        try {
            return converter.fromJson(decrypt, type);
        } catch (Exception e) {
            Logger.e(e, "[Pickle->get]json 反序列化发生异常, key: %s, type: %s", key, type);
        }

        return null;
    }

    public <T> T get(@NonNull String key, Class<? extends T> clazz) {
        return get(key, (Type) clazz);
    }

    /**
     * Gets the saved data, if it is null, default value will be returned
     *
     * @param key          is used to get the saved data
     * @param defaultValue will be return if the response is null
     * @return the saved object
     */
    @SuppressWarnings("unchecked")
    public <T> T get(@NonNull String key, T defaultValue) {
        final T result = get(key, ((Class<? extends T>) defaultValue.getClass()));
        return result != null ? result : defaultValue;
    }

    /**
     * Size of the saved data. Each key will be counted as 1
     *
     * @return the size
     */
    public long count() {
        return storage.count();
    }

    /**
     * Clears the storage, note that crypto data won't be deleted such as salt key etc.
     * Use resetCrypto in order to deleteAll crypto information
     *
     * @return true if deleteAll is successful
     */
    public boolean deleteAll() {
        return storage.deleteAll();
    }

    /**
     * Removes the given key/value from the storage
     *
     * @param key is used for removing related data from storage
     * @return true if delete is successful
     */
    public boolean delete(String key) {
        return storage.delete(key);
    }

    /**
     * Checks the given key whether it exists or not
     *
     * @param key is the key to check
     * @return true if it exists in the storage
     */
    public boolean contains(String key) {
        return storage.contains(key);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Observable／Single API Adapter
    ///////////////////////////////////////////////////////////////////////////

    /**
     * return a Scheduler impl, delegate all pickle operation to a single thread ThreadPool
     *
     * @return Scheduler
     */
    public static Scheduler pickleScheduler() {
        return PICKLE_SCHEDULER.get();
    }

    private static final Supplier<Scheduler> PICKLE_SCHEDULER = Suppliers.synchronizedSupplier(Suppliers.memoize(new Supplier<Scheduler>() {

        @Override
        public Scheduler get() {
            final ExecutorService executor = AndroidExecutors.newFixedThreadPool(1,
                    new TaoThreadFactory("PickleThread-", Process.THREAD_PRIORITY_BACKGROUND, false));

            return Schedulers.from(executor);
        }
    }));

    // 注意所有的异步操作都运行在同一个后台线程中，因此同时的执行多个put操作，last one will win

    /**
     * 异步Put
     * <p>
     * 注意Pickle支持的Observable／Single API 都运行在同一个后台线程中，所以多个连续的调用结果是可预期的，last one win
     */
    public <T> Single<Boolean> putAsync(final @NonNull String key, final @NonNull T value) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return put(key, value);
            }
        }).subscribeOn(pickleScheduler());
    }

    /**
     * 异步 get
     * <p>
     * 注意Pickle支持的Observable/Single API 都运行在同一个后台线程中，所以多个连续的调用结果是可预期的，last one win
     */
    public <T> Single<T> getAsync(final @NonNull String key, @NonNull final Class<? extends T> clazz) {
        return Single.fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return get(key, clazz);
            }
        }).subscribeOn(pickleScheduler());
    }

    /**
     * 异步 get
     * <p>
     * 注意Pickle支持的Observable/Single API 都运行在同一个后台线程中，所以多个连续的调用结果是可预期的，last one win
     */
    public <T> Single<T> getAsync(final @NonNull String key, @NonNull final T defaultValue) {
        return Single.fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return get(key, defaultValue);
            }
        }).subscribeOn(pickleScheduler());
    }

    /**
     * 异步删除
     * <p>
     * 注意Pickle支持的Observable/Single API 都运行在同一个后台线程中，所以多个连续的调用结果是可预期的，last one win
     */
    public Single<Boolean> deleteAsync(final @NonNull String key) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return delete(key);
            }
        }).subscribeOn(pickleScheduler());
    }

    /**
     * 异步删除所有
     * <p>
     * 注意Pickle支持的Observable/Single API 都运行在同一个后台线程中，所以多个连续的调用结果是可预期的，last one win
     */
    public Single<Boolean> deleteAllAsync() {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return deleteAll();
            }
        }).subscribeOn(pickleScheduler());
    }

    /**
     * 异步测试包含关系
     * <p>
     * 注意Pickle支持的Observable/Single API 都运行在同一个后台线程中，所以多个连续的调用结果是可预期的，last one win
     */
    public Single<Boolean> containsAsync(final @NonNull String key) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return contains(key);
            }
        }).subscribeOn(pickleScheduler());
    }

    /**
     * 异步获取数量
     * <p>
     * 注意Pickle支持的Observable/Single API 都运行在同一个后台线程中，所以多个连续的调用结果是可预期的，last one win
     */
    public Single<Long> countAsync() {
        return Single.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return count();
            }
        }).subscribeOn(pickleScheduler());
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    private static boolean isEmpty(String content) {
        if (content == null || content.length() == 0)
            return true;
        else
            return false;
    }

}
