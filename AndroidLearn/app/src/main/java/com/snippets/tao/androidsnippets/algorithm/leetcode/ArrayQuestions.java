package com.snippets.tao.androidsnippets.algorithm.leetcode;

/**
 * Created by Tao He on 2021/5/31.
 * hetaoof@gmail.com
 */
public class ArrayQuestions {

    public static void test() {
        int b[] = new int[] {1,0,2,3,0,4,5,0};
        System.out.println("before:");
        printArray(b);
        duplicateZeros(b);
        System.out.println("after:");
        printArray(b);
    }

    /**
     * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
     *
     * Note that elements beyond the length of the original array are not written.
     *
     * Do the above modifications to the input array in place, do not return anything from your function.
     *
     *
     * Example 1:
     *
     * Input: [1,0,2,3,0,4,5,0]
     * Output: null
     * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
     * Example 2:
     *
     * Input: [1,2,3]
     * Output: null
     * Explanation: After calling your function, the input array is modified to: [1,2,3]
     *
     *
     * Note:
     *
     * 1 <= arr.length <= 10000
     * 0 <= arr[i] <= 9
     *
     */
    public static void duplicateZeros(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) {
                int j = i + 1;
                int end = n - 2;
                while(end >= j) {
                    arr[end + 1] = arr[end];
                    end--;
                }
                arr[j] = 0;
                i++;
            }
        }
    }

    private static void printArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        int len = array.length;
        int count = 0;
        for (int i : array) {
            sb.append(i);
            count++;
            if (count < len) {
                sb.append(",");
            }
        }
        System.out.println(sb);
    }
}
