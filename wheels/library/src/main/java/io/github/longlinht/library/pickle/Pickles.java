package io.github.longlinht.library.pickle;

import io.github.longlinht.library.base.util.DataKeeper;
import io.github.longlinht.library.guava.Supplier;
import io.github.longlinht.library.guava.Suppliers;

import java.io.File;

import io.github.longlinht.library.pickle.impl.Base64Encryption;
import io.github.longlinht.library.pickle.impl.FileStorage;

/**
 * Created by Tao He on 18-5-3.
 * hetaoof@gmail.com
 */
public class Pickles {

    private Pickles() {

        //no instance
    }

    // Default Pickle 的 存储文件夹
    private static final File DEFAULT_PICKLE_DIR = new File(DataKeeper.getAppDataPath(), "pickle");

    private static final Supplier<Pickle> DEFAULT_PICKLE_SUPPLIER = Suppliers.synchronizedSupplier(Suppliers.memoize(new Supplier<Pickle>() {
        @Override
        public Pickle get() {

            return new PickleBuilder()
                    .setStorage(new FileStorage(DEFAULT_PICKLE_DIR))
                    .setEncryption(new Base64Encryption())
                    .createPickle();
        }
    }));

    /**
     * 获取默认的Pickle实例，文件实际存储在外置存储卡上
     */
    public static Pickle getDefaultPickle() {
        return DEFAULT_PICKLE_SUPPLIER.get();
    }

}
