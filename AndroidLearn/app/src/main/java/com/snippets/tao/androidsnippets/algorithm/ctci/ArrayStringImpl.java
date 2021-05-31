package com.snippets.tao.androidsnippets.algorithm.ctci;

import android.util.Log;

import java.util.Arrays;


/**
 * Created by Tao He on 2021/5/25.
 * hetaoof@gmail.com
 */
public class ArrayStringImpl {

    public static void test() {
        //Log.d("htdebug", "ArrayStringImpl test");
        //Log.d("htdebug", "isUniqueChars: " + isUniqueChars("abcdfe"));
        char a[] = new char[] {'a', 'b', 'b', 'd', 'f', 'f'};

        Log.d("htdebug", "before remove: " + new String(a));
        Log.d("htdebug", "after remove: " + removeDuplicates(a));


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


    /**
     * 1.3 Design an algorithm and write code to remove the duplicate characters in a string without
     * using any additional buffer NOTE: One or two additional variables are fine An extra copy of the array is not
     *
     * Write the test cases for this method
     */

    public static String removeDuplicates(char[] a) {
        /*
        if (str == null) return;
        int len = str.length;
        if (len < 2) return;

        int tail = 1;

        for(int i= 1; i < len; ++i) {
           int j;
           for (j = 0; j < tail; ++j) {
               if (str[i] == str[j]) break;
           }

           if (j == tail) {
               str[tail] = str[i];
               ++tail;
           }
        }
        str[tail] = 0;

         */

        if (a == null)
            return new String(a);

        int len = a.length;
        if (len < 2)
            return new String(a);

        int index = 1;

        for(int i=1; i< len; i++) {
            int j;
            for (j=0; j<i; j++) {
                if (a[i] == a[j]) {
                    break;
                }
            }

            if (i == j) {
                a[index++] = a[i];
            }
        }

        return String.valueOf(Arrays.copyOf(a, index));
    }




}
