package io.github.longlinht.library.pickle;

import com.google.gson.Gson;

import io.github.longlinht.library.pickle.impl.GsonConverter;
import io.github.longlinht.library.pickle.impl.NoEncryption;

/**
 * Pickle Builder
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 *
 */
public class PickleBuilder {

    private Converter converter;
    private Encryption encryption;
    private Storage storage;

    /**
     * 设置数据转换器，默认使用基于Gson 的json
     */
    public PickleBuilder setConverter(Converter converter) {
        this.converter = converter;
        return this;
    }

    /**
     * 设置加密方式，默认不进行加密
     */
    public PickleBuilder setEncryption(Encryption encryption) {
        this.encryption = encryption;
        return this;
    }

    /**
     * 配置storage 方式
     */
    public PickleBuilder setStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    /**
     * 创建pickle对象
     */
    public Pickle createPickle() {

        if (encryption == null) {
            encryption = new NoEncryption();
        }

        if (converter == null) {
            converter = new GsonConverter(new Gson());
        }


        return new Pickle(converter, encryption, storage);
    }
}