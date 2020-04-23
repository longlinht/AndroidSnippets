package com.snippets.tao.androidsnippets.demo;

import android.util.Log;

import java.util.*;

/**
 * Created by Tao He on 19-8-2.
 * hetaoof@gmail.com
 */
public class AgorithmImpl {

    public static void test() {
        List<Integer> listA = new ArrayList<>();
        listA.add(1);
        listA.add(3);
        listA.add(8);
        listA.add(9);
        listA.add(15);

        List<Integer> listB = new ArrayList<>();
        listB.add(2);
        listB.add(3);
        listB.add(4);
        listB.add(9);

        //arraySub(listA, listB);
        //arraySubBySet(listA, listB);

        /*
        int[] a = new int[] {4, 5, 8, 2, 7, 1};
        insertSort(a);

        for (int i : a ) {
            Log.d("htdebug", "result:" + i);
        }

        Log.d("htdebug", "abcd isAllUnique:" + isUniqueChars2("abcd"));
        Log.d("htdebug", "hello isAllUnique:" + isUniqueChars2("hello"));
        Log.d("htdebug", "aaabbbbc isAllUnique:" + isUniqueChars2("aaabbbbc"));

        char a[] = new char[] {'a', 'b', 'b', 'd'};
        Log.d("htdebug", "before:" +new String(a));
        Log.d("htdebug", "after:" + removeDuplicates(a));
         */

        reverse("abcdefgh");

    }

    /**
     ***************************************************
     *
     * a is array, b is array
     * a - b = remove element in a and in b
     *
     ***************************************************
     */

    public static void arraySub(List<Integer> listA, List<Integer> listB) {

        Iterator<Integer> iteratorA = listA.iterator();
        Iterator<Integer> iteratorB = listB.iterator();
        while (iteratorB.hasNext()) {
            int tempB = iteratorB.next();

            boolean match = false;
            int tempA;

            iteratorA = listA.iterator();

            while (iteratorA.hasNext()) {
                tempA = iteratorA.next();
                if (tempA == tempB) {
                    iteratorA.remove();
                    iteratorB.remove();
                    match = true;
                    break;
                }
            }

            if (!match) {
                listA.add(tempB);
                iteratorB.remove();
            }
        }

        iteratorA = listA.iterator();
        while (iteratorA.hasNext()) {
            Log.d("htdebug", "result:" + iteratorA.next());
        }
    }

    public static void arraySubBySet(List<Integer> listA, List<Integer> listB) {
        Set<Integer> sets = new HashSet<>();

        Iterator<Integer> iteratorA = listA.iterator();
        Iterator<Integer> iteratorB = listB.iterator();

        while (iteratorA.hasNext()) {
            sets.add(iteratorA.next());
        }

        while (iteratorA.hasNext()) {
            sets.add(iteratorB.next());
        }

        Iterator<Integer> setsInterator = sets.iterator();
        while (setsInterator.hasNext()) {
            Log.d("htdebug", "result:" + iteratorA.next());
        }
    }

    /**
     *********************************************************************************
     *
     */


    // insert sort
    public static void insertSort(int[] array) {
        int size = array.length;

        for(int i=1; i<size; i++) {
            int key = array[i];

            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
    }

    /**
     *
     * *******************************************************************************************************
     *
     * 1.1 Implement an algorithm to determine if a string has all unique characters What if you can not use
     * additional data structures?
     *
     * *******************************************************************************************************
     *
     */

    public static boolean isUniqueChars(final String s) {
        if (s.length() > 128)
            return false;

        boolean charSet[] = new boolean[128];
        for(int i=0; i<s.length(); i++) {
            int value = s.charAt(i);
            if (charSet[value]) {
                return false;
            } else {
                charSet[value] = true;
            }
        }
        return true;
    }

    public static boolean isUniqueChars2(String str) {
        if (str.length() > 128) {
            return false;
        }

        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            //int val = str.charAt(i) - 'a';
            int val = str.charAt(i);
            Log.d("htdebug", "val=" + val);
            Log.d("htdebug", "1<<val=" + (1 << val));
            Log.d("htdebug", "checker & (1 << val)=" + (checker & (1 << val)));
            if ((checker & (1 << val)) > 0) return false;
            checker |= (1 << val);
            Log.d("htdebug", "checker=" + checker);
        }
        return true;
    }


    /**
     * *******************************************************************************************************
     * Write code to reverse a C-Style String (C-String means that "abcd" is represented as five characters,
     * including the null character )
     */

    public static void reverseString(char[] chars) {
        if (chars == null)
            return;

        if (chars.length <= 1) {
            return;
        }

        int i = 0;
        int j = chars.length - 1;
        while(i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }

    public static void reverse(String str)
    {
        if ((str==null)||(str.length() <= 1)){
            System.out.println(str);
        }
        else
        {
            System.out.print(str.charAt(str.length()-1));
            reverse(str.substring(0,str.length()-1));
        }
    }

    /**
     *
     * *******************************************************************************************************
     */


    /**
     *
     * *******************************************************************************************************
     * Design an algorithm and write code to remove the duplicate characters in a string without using any
     * additional buffer NOTE: One or two additional variables are fine An extra copy of the array is not
     * FOLLOW UP
     * Write the test cases for this method
     * *******************************************************************************************************
     *
     */

    public static String removeDuplicates(char[] a) {
        if (a == null)
            return new String(a);

        int len = a.length;
        if (len < 2)
            return new String(a);

        int index = 0;

        for(int i=0; i< len; i++) {
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

    /**
     *
     * *******************************************************************************************************
     */


    /**
     *
     * *******************************************************************************************************
     *
     * Write a method to decide if two strings are anagrams or not
     *
     * *******************************************************************************************************
     *
     */




    /**
     *
     * *******************************************************************************************************
     */

}
