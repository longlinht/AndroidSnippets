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

    private static void swap(int array[], int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void test() {
        int b[] = new int[] {1,0,2,3,0,4,5,0};

        //int[] nums1 = new int[] {1, 2, 3, 0, 0, 0};
        //int[] nums2 = new int[] {2, 5, 6};

        //int[] nums1 = new int[] {2, 0};
        //int[] nums2 = new int[] {1};

        //int[] nums = new int[] {0,1,2,2,3,0,4,2};
        int[] nums = new int[] {3, 2, 2, 3};

        System.out.println("before:");
        printArray(nums);

        //merge(nums1, 1, nums2, 1);
        int length = removeElement(nums, 3);

        System.out.println("after:");
        printArray(nums);
        System.out.println("length:" + length);
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

    /**
     * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     *
     * Clarification:
     *
     * Confused why the returned value is an integer but your answer is an array?
     *
     * Note that the input array is passed in by reference, which means a modification to the input array will be known to the caller as well.
     *
     * Internally you can think of this:
     *
     * // nums is passed in by reference. (i.e., without making a copy)
     * int len = removeElement(nums, val);
     *
     * // any modification to nums in your function would be known by the caller.
     * // using the length returned by your function, it prints the first len elements.
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     * }
     *
     *
     * Example 1:
     *
     * Input: nums = [3,2,2,3], val = 3
     * Output: 2, nums = [2,2]
     * Explanation: Your function should return length = 2, with the first two elements of nums being 2.
     * It doesn't matter what you leave beyond the returned length. For example if you return 2 with nums = [2,2,3,3] or nums = [2,2,0,0], your answer will be accepted.
     * Example 2:
     *
     * Input: nums = [0,1,2,2,3,0,4,2], val = 2
     * Output: 5, nums = [0,1,4,0,3]
     * Explanation: Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4. Note that the order of those five elements can be arbitrary. It doesn't matter what values are set beyond the returned length.
     *
     *
     * Constraints:
     *
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     *
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int i = 0;
        int j = n - 1;
        while(j > i) {
            if (nums[i] == val && nums[j] != val) {
                swap(nums, i, j);
                i++;
                j--;
            } else if (nums[i] != val && nums[j] == val) {
                i++;
                j--;
            }  else if (nums[i] != val && nums[j] != val) {
                i++;
            } else if (nums[i] == val && nums[j] == val) {
                j--;
            }
        }

        if(nums[i] != val) {
            return i + 1;
        } else {
            return i;
        }
    }
}
