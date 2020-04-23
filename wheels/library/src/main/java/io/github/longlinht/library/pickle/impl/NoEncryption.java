package io.github.longlinht.library.pickle.impl;

import io.github.longlinht.library.pickle.Encryption;

/**
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 *
 */


public class NoEncryption implements Encryption {

    @Override
    public String encrypt(String key, String value) throws Exception {
        return value;
    }

    @Override
    public String decrypt(String key, String value) throws Exception {
        return value;
    }
}
