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

        int[] a = new int[] {4, 5, 8, 2, 7, 1};
        insertSort(a);

        for (int i : a ) {
            Log.d("htdebug", "result:" + i);
        }
    }
    // a is array, b is array
    // a - b = remove element in a and in b
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

}
