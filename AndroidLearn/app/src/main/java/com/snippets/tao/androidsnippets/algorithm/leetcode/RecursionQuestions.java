package com.snippets.tao.androidsnippets.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tao He on 2021/6/17.
 * hetaoof@gmail.com
 */
public class RecursionQuestions {

    public void test() {
        //myPow(2, -2);
        //generateTrees(3);

        int[][] matrix = new int[][]
                {
                        {1,4,7,11,15},
                        {2,5,8,12,19},
                        {3,6,9,16,22},
                        {10,13,14,17,24},
                        {18,21,23,26,30}
                };

        int[][] matrix2 = new int[][]
                {
                        {1,4},
                };

        searchMatrix(matrix2, 20);

    }

    /**
     *
     * Reverse String
     *
     * Write a function that reverses a string. The input string is given as an array of characters s.
     *
     * Example 1:
     *
     * Input: s = ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     * Example 2:
     *
     * Input: s = ["H","a","n","n","a","h"]
     * Output: ["h","a","n","n","a","H"]
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 105
     * s[i] is a printable ascii character.
     *
     *
     * Follow up: Do not allocate extra space for another array.
     * You must do this by modifying the input array in-place with O(1) extra memory.
     *
     *
     */

    private int n;
    public void reverseString(char[] s) {
        if (s == null || s.length == 0)
            return;
        n = s.length;
        helper(0, s);
    }

    private void helper(int index, char [] str) {
        if (str == null || index >= n / 2) {
            return;
        }

        char temp = str[index];
        str[index] = str[n - index - 1];
        str[n - index - 1] = temp;
        System.out.print(str[index]);

        helper(index + 1, str);
    }

    /**
     *
     * Search in a Binary Search Tree
     *
     * You are given the root of a binary search tree (BST) and an integer val.
     *
     * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
     * If such a node does not exist, return null.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [4,2,7,1,3], val = 2
     * Output: [2,1,3]
     * Example 2:
     *
     *
     * Input: root = [4,2,7,1,3], val = 5
     * Output: []
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 5000].
     * 1 <= Node.val <= 107
     * root is a binary search tree.
     * 1 <= val <= 107
     *
     *
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;

        if (root.val == val)
            return root;

        TreeNode left = searchBST(root.left, val);
        if(left != null)
            return left;

        TreeNode right = searchBST(root.right, val);
        if(right != null)
            return right;

        return null;
    }

    /**
     * Pascal's Triangle II
     *
     * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     *
     *
     *
     *
     * Example 1:
     *
     * Input: rowIndex = 3
     * Output: [1,3,3,1]
     * Example 2:
     *
     * Input: rowIndex = 0
     * Output: [1]
     * Example 3:
     *
     * Input: rowIndex = 1
     * Output: [1,1]
     *
     *
     * Constraints:
     *
     * 0 <= rowIndex <= 33
     *
     *
     * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
     *
     *
     */

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0)
            return null;

        if (rowIndex == 0) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            return list;
        }

        if (rowIndex == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(1);
            return list;
        }

        List<Integer> output = new ArrayList<>();
        output.add(1);
        List<Integer> preList = getRow(rowIndex - 1);
        int size = preList.size();
        int i = 0;
        int j = 1;

        while(j <= size - 1) {
            output.add(preList.get(i) + preList.get(j));
            i++;
            j++;
        }

        output.add(1);
        return output;
    }

    /**
     *
     * Fibonacci Number
     *
     * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
     * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
     *
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * Given n, calculate F(n).
     *
     *
     *
     * Example 1:
     *
     * Input: n = 2
     * Output: 1
     * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
     * Example 2:
     *
     * Input: n = 3
     * Output: 2
     * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
     * Example 3:
     *
     * Input: n = 4
     * Output: 3
     * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
     *
     *
     * Constraints:
     *
     * 0 <= n <= 30
     *
     *
     */
    private Map<Integer, Integer> cache = new HashMap<>();
    public int fib(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        if (n < 2) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }

    /**
     *
     * Climbing Stairs
     *
     * You are climbing a staircase. It takes n steps to reach the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     *
     *
     * Example 1:
     *
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:
     *
     * Input: n = 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     *
     * n = 4
     *
     * 1. 1 step + 1 step + 1 step + + 1 step
     * 2. 1 step + 1 step + 2 step
     * 3. 1 step + 2 step + 1 step
     * 4. 2 step + 1 step + 1 step
     * 5. 2 step + 2 step
     *
     *
     *
     *
     * Constraints:
     *
     * 1 <= n <= 45
     *
     *
     */
    public int climbStairs(int n) {
        if(n == 0 || n == 1 || n == 2){return n;}
        int[] mem = new int[n];
        mem[0] = 1;
        mem[1] = 2;
        for(int i = 2; i < n; i++){
            mem[i] = mem[i-1] + mem[i-2];
        }
        return mem[n-1];
    }

    /**
     * Pow(x, n)
     *
     * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
     *
     *
     *
     * Example 1:
     *
     * Input: x = 2.00000, n = 10
     * Output: 1024.00000
     * Example 2:
     *
     * Input: x = 2.10000, n = 3
     * Output: 9.26100
     * Example 3:
     *
     * Input: x = 2.00000, n = -2
     * Output: 0.25000
     * Explanation: 2-2 = 1/22 = 1/4 = 0.25
     *
     *
     * Constraints:
     *
     * -100.0 < x < 100.0
     * -231 <= n <= 231-1
     * -104 <= xn <= 104
     *
     *
     */
    public double myPow(double x, int n) {

        if (n == 0) return 1;
        double half = myPow(x, n / 2);

        if (n % 2 == 0) return half * half;
        if (n > 0) return half * half * x;

        return half * half / x;
    }


    /**
     *
     * K-th Symbol in Grammar
     * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row,
     * we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
     *
     * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
     * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 1, k = 1
     * Output: 0
     * Explanation: row 1: 0
     * Example 2:
     *
     * Input: n = 2, k = 1
     * Output: 0
     * Explanation:
     * row 1: 0
     * row 2: 01
     * Example 3:
     *
     * Input: n = 2, k = 2
     * Output: 1
     * Explanation:
     * row 1: 0
     * row 2: 01
     * Example 4:
     *
     * Input: n = 3, k = 1
     * Output: 0
     * Explanation:
     * row 1: 0
     * row 2: 01
     * row 3: 0110
     *
     *
     * Constraints:
     *
     * 1 <= n <= 30
     * 1 <= k <= 2n - 1
     *
     *
     */
    public int kthGrammar(int n, int k) {
        if( n == 1 )
            return 0;
        if(k % 2 == 0){
            if (kthGrammar(n - 1,k / 2 )== 0)
                return 1;
            else
                return 0;
        } else{
            if(kthGrammar(n - 1,(k + 1) / 2) == 0)
                return 0;
            else
                return 1;
        }
    }

    /**
     *
     * Unique Binary Search Trees II
     *
     * Given an integer n, return all the structurally unique BST's (binary search trees),
     * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 3
     * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
     * Example 2:
     *
     * Input: n = 1
     * Output: [[1]]
     *
     *
     * Constraints:
     *
     * 1 <= n <= 8
     *
     *
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = genTreeList(1, n);
        return list;
    }

    private List<TreeNode> genTreeList(int start, int end) {

        List<TreeNode> list = new ArrayList<TreeNode>();
        if (start > end) {
            list.add(null);
        }
        for(int idx = start; idx <= end; idx++) {
            List<TreeNode> leftList = genTreeList(start, idx - 1);
            List<TreeNode> rightList = genTreeList(idx + 1, end);
            for (TreeNode left : leftList) {
                for(TreeNode right: rightList) {
                    TreeNode root = new TreeNode(idx);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }

    /**
     *
     * Sort an Array
     * Given an array of integers nums, sort the array in ascending order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [5,2,3,1]
     * Output: [1,2,3,5]
     * Example 2:
     *
     * Input: nums = [5,1,1,2,0,0]
     * Output: [0,0,1,1,2,5]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 104
     * -5 * 104 <= nums[i] <= 5 * 104
     *
     *
     */

    public int[] sortArray(int[] nums) {
        int N = nums.length;
        mergeSort(nums, 0, N-1);
        return nums;
    }


    void mergeSort(int[] nums, int start, int end){
        if (end - start < 0) return; //Already sorted.
        int mi = start + (end - start)/ 2;
        mergeSort(nums, start, mi);
        mergeSort(nums, mi+1, end);
        merge(nums, start,mi, end);
    }

    void merge(int[] nums, int start, int mi, int end){
        int lp = start;
        int rp = mi + 1;
        int[] buffer = new int[end-start+1];
        int t = 0; //buffer pointer

        while (lp <= mi && rp <= end){
            if (nums[lp] < nums[rp]){
                buffer[t++] = nums[lp++];
            }else{
                buffer[t++] = nums[rp++];
            }
        }

        while (lp <= mi) buffer[t++] = nums[lp++];
        while (rp <= end) buffer[t++] = nums[rp++];
        //Now copy sorted buffer into original array
        for (int i = start; i <= end; i++){
            nums[i] = buffer[i-start];
        }
    }


    /**
     *
     * Validate Binary Search Tree
     *
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     *
     * A valid BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     *
     * Example 1:
     *
     *
     * Input: root = [2,1,3]
     * Output: true
     * Example 2:
     *
     *
     * Input: root = [5,1,4,null,null,3,6]
     * Output: false
     * Explanation: The root node's value is 5 but its right child's value is 4.
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 104].
     * -231 <= Node.val <= 231 - 1
     *
     *
     */

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    /**
     *
     * Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
     *
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     *
     *
     * Example 1:
     *
     *
     * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
     * Output: true
     * Example 2:
     *
     *
     * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
     * Output: false
     *
     *
     * Constraints:
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= n, m <= 300
     * -109 <= matix[i][j] <= 109
     * All the integers in each row are sorted in ascending order.
     * All the integers in each column are sorted in ascending order.
     * -109 <= target <= 109
     *
     *
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int n = matrix.length, m = matrix[0].length;
        int i = 0, j = m - 1;

        while (i < n && j >= 0) {
            int num = matrix[i][j];

            if (num == target)
                return true;
            else if (num > target)
                j--;
            else
                i++;
        }

        return false;
    }

}
