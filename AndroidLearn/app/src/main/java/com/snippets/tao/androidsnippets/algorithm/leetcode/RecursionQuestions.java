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
}
