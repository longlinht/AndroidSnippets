package com.snippets.tao.androidsnippets.algorithm.leetcode;

/**
 * Created by Tao He on 2021/7/5.
 * hetaoof@gmail.com
 */
public class BinarySearchQuestions {

    public void test() {
        int[] a = new int[] {-1,0,3,5,9,12};
        //search(a, 9);
        mySqrt(8);
    }


    /**
     *
     * Binary Search
     *
     * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
     *
     * You must write an algorithm with O(log n) runtime complexity.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [-1,0,3,5,9,12], target = 9
     * Output: 4
     * Explanation: 9 exists in nums and its index is 4
     * Example 2:
     *
     * Input: nums = [-1,0,3,5,9,12], target = 2
     * Output: -1
     * Explanation: 2 does not exist in nums so return -1
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * -104 < nums[i], target < 104
     * All the integers in nums are unique.
     * nums is sorted in ascending order.
     *
     *
     */

public int search(int[] nums, int target) {

    if (nums == null || nums.length == 0)
        return -1;

    int left = 0;
    int right = nums.length - 1;
    while(left <= right) {
        int mid = left + (right - left) / 2;
        if(target == nums[mid]) {
            return mid;
        } else if (target < nums[mid]) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return -1;
}

    /**
     *
     * Sqrt(x)
     *
     * Given a non-negative integer x, compute and return the square root of x.
     *
     * Since the return type is an integer, the decimal digits are truncated,
     * and only the integer part of the result is returned.
     *
     * Note: You are not allowed to use any built-in exponent function or operator,
     * such as pow(x, 0.5) or x ** 0.5.
     *
     *
     * Example 1:
     *
     * Input: x = 4
     * Output: 2
     * Example 2:
     *
     * Input: x = 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
     *
     *
     * Constraints:
     *
     * 0 <= x <= 231 - 1
     *
     *
     *
     */

    public int mySqrt(int x) {
        if (x == 0) return 0;
        int start = 1, end = x;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid <= x / mid && (mid + 1) > x / (mid + 1))// Found the result
                return mid;
            else if (mid > x / mid)// Keep checking the left part
                end = mid;
            else
                start = mid + 1;// Keep checking the right part
        }
        return start;
    }


    /**
     * Guess Number Higher or Lower
     *
     * We are playing the Guess Game. The game is as follows:
     *
     * I pick a number from 1 to n. You have to guess which number I picked.
     *
     * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
     *
     * You call a pre-defined API int guess(int num), which returns 3 possible results:
     *
     * -1: The number I picked is lower than your guess (i.e. pick < num).
     * 1: The number I picked is higher than your guess (i.e. pick > num).
     * 0: The number I picked is equal to your guess (i.e. pick == num).
     * Return the number that I picked.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 10, pick = 6
     * Output: 6
     * Example 2:
     *
     * Input: n = 1, pick = 1
     * Output: 1
     * Example 3:
     *
     * Input: n = 2, pick = 1
     * Output: 1
     * Example 4:
     *
     * Input: n = 2, pick = 2
     * Output: 2
     *
     *
     * Constraints:
     *
     * 1 <= n <= 231 - 1
     * 1 <= pick <= n
     *
     *
     *
     */

    public int guessNumber(int n) {

        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int result = guess(mid);
            if (result == -1) {
                end = mid - 1;
            } else if (result == 1) {
                start = mid + 1;
            } else if (result == 0) {
                return mid;
            }
        }
        return start;
    }

    /**
     *
     * Search in Rotated Sorted Array
     *
     * There is an integer array nums sorted in ascending order (with distinct values).
     *
     * Prior to being passed to your function, nums is rotated at an unknown pivot index
     * k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ...,
     * nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7]
     * might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
     *
     * Given the array nums after the rotation and an integer target, return the index of
     * target if it is in nums, or -1 if it is not in nums.
     *
     * You must write an algorithm with O(log n) runtime complexity.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * Example 2:
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     * Example 3:
     *
     * Input: nums = [1], target = 0
     * Output: -1
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5000
     * -104 <= nums[i] <= 104
     * All values of nums are unique.
     * nums is guaranteed to be rotated at some pivot.
     * -104 <= target <= 104
     *
     *
     *
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int n = nums.length;
        int pivot = -1;
        for(int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                pivot = i;
            }
        }

        if (pivot <= 0) {
            return searchSub(nums, 0, n - 1, target);
        }

        int leftRes = searchSub(nums, 0, pivot - 1, target);
        int rightRes = searchSub(nums, pivot, n - 1, target);

        if (leftRes != -1)
            return leftRes;

        if (rightRes != -1)
            return rightRes;

        return -1;
    }

    private int searchSub(int[] nums, int start, int end, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = start;
        int right = end;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }


    /**
     *
     * First Bad Version
     *
     * You are a product manager and currently leading a team to develop a new product. Unfortunately,
     * the latest version of your product fails the quality check. Since each version is developed based
     * on the previous version, all the versions after a bad version are also bad.
     *
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
     * which causes all the following ones to be bad.
     *
     * You are given an API bool isBadVersion(version) which returns whether version is bad.
     * Implement a function to find the first bad version. You should minimize the number of calls to the API.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 5, bad = 4
     * Output: 4
     * Explanation:
     * call isBadVersion(3) -> false
     * call isBadVersion(5) -> true
     * call isBadVersion(4) -> true
     * Then 4 is the first bad version.
     * Example 2:
     *
     * Input: n = 1, bad = 1
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= bad <= n <= 231 - 1
     *
     */
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            boolean bad = isBadVersion(mid);
            if (bad) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     *
     * Find Peak Element
     *
     * A peak element is an element that is strictly greater than its neighbors.
     *
     * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks,
     * return the index to any of the peaks.
     *
     * You may imagine that nums[-1] = nums[n] = -∞.
     *
     * You must write an algorithm that runs in O(log n) time.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,1]
     * Output: 2
     * Explanation: 3 is a peak element and your function should return the index number 2.
     * Example 2:
     *
     * Input: nums = [1,2,1,3,5,6,4]
     * Output: 5
     * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * -231 <= nums[i] <= 231 - 1
     * nums[i] != nums[i + 1] for all valid i.
     *
     *
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        int n = nums.length;
        if (n == 1)
            return 0;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if(nums[i] > nums[i+1]) {
                    return i;
                } else {
                    continue;
                }
            }

            if (i == n - 1 && nums[i] > nums[i-1]) {
                return i;
            }

            if (nums[i] > nums[i-1] && nums[i] > nums[i+1]) {
                return i;
            }
        }

        return -1;
    }
}
