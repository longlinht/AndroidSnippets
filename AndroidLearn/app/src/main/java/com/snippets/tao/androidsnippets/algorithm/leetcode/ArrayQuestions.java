package com.snippets.tao.androidsnippets.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        //int[] nums = new int[] {3, 2, 2, 3};
        //int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        //int[] nums = new int[] {3,1,7,11};
        //int[] nums = new int[] {-2,0,10,-19,4,6,-8};
        //int[] nums = new int[] {0, 0};
        //int[] nums = new int[] {2,1,2,3,5,7,9,10,12,14,15,16,18,14,13};
        int[] nums = new int[] {0,1,0,3,12};

        System.out.println("before:");
        printArray(nums);

        //merge(nums1, 1, nums2, 1);
        //int length = removeElement(nums, 3);
        //int length = removeDuplicates(nums);
        //boolean exists = checkIfExist(nums);
        //boolean valid = validMountainArray(nums);
        //System.out.println("valid:" + valid);
        moveZeroes(nums);

        System.out.println("after:");
        printArray(nums);
        //System.out.println("length:" + length);
    }

    /**
     *  LeetCode 485
     *
     * Given a binary array nums, return the maximum number of consecutive 1's in the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,0,1,1,1]
     * Output: 3
     * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
     * Example 2:
     *
     * Input: nums = [1,0,1,1,0,1]
     * Output: 2
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 105
     * nums[i] is either 0 or 1.
     *
     *
     *
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                ++count;
                max = Math.max(count, max);
            } else {
                count = 0;
            }
        }
        return max;
    }

    /** LeetCode 977. Squares of a Sorted Array
     *
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     * Example 2:
     *
     * Input: nums = [-7,-3,2,3,11]
     * Output: [4,9,9,49,121]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums is sorted in non-decreasing order.
     *
     *
     * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
     *
     *
     */

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        Arrays.sort(nums);
        for(int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }
        return nums;
    }

    /**
     * LeetCode 1295. Find Numbers with Even Number of Digits
     *
     * Given an array nums of integers, return how many of them contain an even number of digits.
     *
     *
     * Example 1:
     *
     * Input: nums = [12,345,2,6,7896]
     * Output: 2
     * Explanation:
     * 12 contains 2 digits (even number of digits).
     * 345 contains 3 digits (odd number of digits).
     * 2 contains 1 digit (odd number of digits).
     * 6 contains 1 digit (odd number of digits).
     * 7896 contains 4 digits (even number of digits).
     * Therefore only 12 and 7896 contain an even number of digits.
     * Example 2:
     *
     * Input: nums = [555,901,482,1771]
     * Output: 1
     * Explanation:
     * Only 1771 contains an even number of digits.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 500
     * 1 <= nums[i] <= 10^5
     *
     */

    public static int findNumbers(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int count = 0;
        for(int i = 0; i<nums.length; i++) {
            if(nums[i] > 9 && nums[i] <100
                    || nums[i] > 999 && nums[i] < 10000
                    || nums[i] > 99999 && nums[i] < 1000000) {
                count++;
            }
        }
        return count;
    }

    /**
     * LeetCode 1089. Duplicate Zeros
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
     * LeetCode 27
     *
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

    /**
     * LeetCode 26
     *
     * Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
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
     * int len = removeDuplicates(nums);
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
     * Input: nums = [1,1,2]
     * Output: 2, nums = [1,2]
     * Explanation: Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the returned length.
     * Example 2:
     *
     * Input: nums = [0,0,1,1,1,2,2,3,3,4]
     * Output: 5, nums = [0,1,2,3,4]
     * Explanation: Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond the returned length.
     *
     *
     * Constraints:
     *
     * 0 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums is sorted in ascending order.
     *
     */

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int cur = nums[0];
        int preIndex = 0;
        for(int i = 0; i < n; i++) {
            if (nums[i] != cur) {
                preIndex++;
                nums[preIndex] = nums[i];
                cur = nums[i];
            }
        }
        return preIndex + 1;
    }


    /**
     *
     * LeetCode 1346. Check If N and Its Double Exist
     *
     * Given an array arr of integers, check if there exists two integers N and M such that N is the double of
     * M ( i.e. N = 2 * M).
     *
     * More formally check if there exists two indices i and j such that :
     *
     * i != j
     * 0 <= i, j < arr.length
     * arr[i] == 2 * arr[j]
     *
     *
     * Example 1:
     *
     * Input: arr = [10,2,5,3]
     * Output: true
     * Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
     *
     * Example 2:
     *
     * Input: arr = [7,1,14,11]
     * Output: true
     * Explanation: N = 14 is the double of M = 7,that is, 14 = 2 * 7.
     * Example 3:
     *
     * Input: arr = [3,1,7,11]
     * Output: false
     * Explanation: In this case does not exist N and M, such that N = 2 * M.
     *
     *
     * Constraints:
     *
     * 2 <= arr.length <= 500
     * -10^3 <= arr[i] <= 10^3
     *
     */
    public static boolean checkIfExist(int[] arr) {
        if (arr == null || arr.length == 0)
            return false;
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                map.put(arr[i], i);
            } else {
                zeroCount++;
            }

            if (zeroCount > 1) {
                return true;
            }
        }

        for (int i = 0; i < n; i++) {
            int twoTimes = arr[i] * 2;
            if (map.get(twoTimes) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * LeetCode 941
     *
     * Given an array of integers arr, return true if and only if it is a valid mountain array.
     *
     * Recall that arr is a mountain array if and only if:
     *
     * arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [2,1]
     * Output: false
     *
     * Example 2:
     *
     * Input: arr = [3,5,5]
     * Output: false
     *
     * Example 3:
     *
     * Input: arr = [0,3,2,1]
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 104
     * 0 <= arr[i] <= 104
     *
     */

    public static boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3)
            return false;

        int maxIndex = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }

        if (maxIndex == 0 || maxIndex == n-1)
            return false;

        for (int i = maxIndex; i > 0; i--) {
            if (arr[i] <= arr[i - 1]) {
                return false;
            }
        }

        for (int i = maxIndex; i < n - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * LeetCode 1299. Replace Elements with Greatest Element on Right Side
     *
     * Given an array arr, replace every element in that array with the greatest element among the elements to its right,
     * and replace the last element with -1.
     *
     * After doing so, return the array.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [17,18,5,4,6,1]
     * Output: [18,6,6,6,1,-1]
     * Explanation:
     * - index 0 --> the greatest element to the right of index 0 is index 1 (18).
     * - index 1 --> the greatest element to the right of index 1 is index 4 (6).
     * - index 2 --> the greatest element to the right of index 2 is index 4 (6).
     * - index 3 --> the greatest element to the right of index 3 is index 4 (6).
     * - index 4 --> the greatest element to the right of index 4 is index 5 (1).
     * - index 5 --> there are no elements to the right of index 5, so we put -1.
     * Example 2:
     *
     * Input: arr = [400]
     * Output: [-1]
     * Explanation: There are no elements to the right of index 0.
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 104
     * 1 <= arr[i] <= 105
     *
     *
     */

    public static int[] replaceElements(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int max = Integer.MIN_VALUE;
            int maxIndex = i + 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                    maxIndex = j;
                }
            }

            if (i == n -1) {
                arr[i] = -1;
            } else {
                arr[i] = arr[maxIndex];
            }
        }
        return arr;
    }

    /**
     * LeetCode 283. Move Zeroes
     *
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     *
     * Note that you must do this in-place without making a copy of the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [0]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     *
     *
     * Follow up: Could you minimize the total number of operations done?
     *
     *
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int n = nums.length;
        int i = 0;
        int j = i + 1;
        while(j < n) {
            if (nums[i] == 0 && nums[j] != 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[i] == 0 && nums[j] == 0) {
                j++;
            }  else {
                i++;
                j++;
            }
        }
    }
}

























