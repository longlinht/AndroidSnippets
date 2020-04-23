package io.github.longlinht.library.pickle.impl;

import android.util.Base64;

import io.github.longlinht.library.pickle.Encryption;

import java.nio.charset.Charset;

/**
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 */

public class Base64Encryption implements Encryption {

// 【Base64】
//  1. base64的编码都是按字符串长度，以每3个8bit的字符为一组，
//  2. 然后针对每组，首先获取每个字符的ASCII编码，
//  3. 然后将ASCII编码转换成8bit的二进制，得到一组3*8=24bit的字节
//  4. 然后再将这24bit划分为4个6bit的字节，并在每个6bit的字节前面都填两个高位0，得到4个8bit的字节
//  5. 然后将这4个8bit的字节转换成10进制，对照Base64编码表 （下表），得到对应编码后的字符。
//
//    按照这个算法分析的话，应该是O(n)的时间复杂度（编解码）

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    public String encrypt(String key, String value) throws Exception {
        return Base64.encodeToString(value.getBytes(charset), Base64.DEFAULT);
    }

    @Override
    public String decrypt(String key, String value) throws Exception {
        return new String(Base64.decode(value, Base64.DEFAULT), charset);
    }
}
