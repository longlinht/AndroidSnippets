package com.snippets.tao.androidsnippets.algorithm.ctci;

import android.util.Log;

/**
 * Created by Tao He on 2021/5/25.
 * hetaoof@gmail.com
 */
public class ArrayStringImpl {

    public static void test() {
        Log.d("htdebug", "ArrayStringImpl test");
        Log.d("htdebug", "isUniqueChars: " + isUniqueChars("abcdfe"));
    }

    /**
     * 1.1 Implement an algorithm to determine if a string has all unique characters
     * What if you can not use additional data structures?
     */

    public static boolean isUniqueChars(String str) {
        int checker = 0;
        for(int i = 0; i<str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) return false;

            checker |= (1 << val);
        }
        return true;
    }

}
