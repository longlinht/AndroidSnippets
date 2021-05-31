package com.snippets.tao.androidsnippets.algorithm.leetcode;

/**
 * Created by Tao He on 2021/5/31.
 * hetaoof@gmail.com
 */
public class ArrayQuestions {

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

    public static void test() {
        int b[] = new int[] {1,0,2,3,0,4,5,0};

        //int[] nums1 = new int[] {1, 2, 3, 0, 0, 0};
        //int[] nums2 = new int[] {2, 5, 6};

        int[] nums1 = new int[] {2, 0};
        int[] nums2 = new int[] {1};

        System.out.println("before:");
        printArray(nums1);

        merge(nums1, 1, nums2, 1);

        System.out.println("after:");
        printArray(nums1);
    }

    /**
     * LeetCode 1089
     *
     * Given a fixed length array arr of integers, duplicate each occurrence of zero,
     * shifting the remaining elements to the right.
     * Note that elements beyond the length of the original array are not written.
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


    /**
     * LeetCode 88
     *
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
     * representing the number of elements in nums1 and nums2 respectively.
     *
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     *
     * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
     * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should
     * be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     * Example 2:
     *
     * Input: nums1 = [1], m = 1, nums2 = [], n = 0
     * Output: [1]
     * Explanation: The arrays we are merging are [1] and [].
     * The result of the merge is [1].
     * Example 3:
     *
     * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
     * Output: [1]
     * Explanation: The arrays we are merging are [] and [1].
     * The result of the merge is [1].
     * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
     *
     *
     * Constraints:
     *
     * nums1.length == m + n
     * nums2.length == n
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     * -109 <= nums1[i], nums2[j] <= 109
     *
     *
     * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
     *
     *
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int pointer1 = m - 1;
        int pointer2 = n - 1;
        int pointer3 = m + n -1;

        while(pointer2 >= 0 && pointer1 >= 0) {
            if(nums1[pointer1] <= nums2[pointer2]) {
                nums1[pointer3] = nums2[pointer2];
                pointer2--;
            }  else {
                nums1[pointer3] = nums1[pointer1];
                pointer1--;
            }
            pointer3--;
        }

        while(pointer2 >= 0) {
            nums1[pointer3] = nums2[pointer2];
            pointer3--;
            pointer2--;
        }
    }
}
