package com.snippets.tao.androidsnippets.algorithm.leetcode;

import com.snippets.tao.androidsnippets.logger.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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

        //searchMatrix(matrix2, 20);

        //solveNQ();
        //int n = 15;
        //System.out.println(n + " queens has " + totalNQueens(n) + " solutions");
        //combine(4, 2);
        //generateParenthesis(3);
        //int[] heights = new int[]{2,1,5,6,2,3};
        //int largest = largestRectangleArea(heights);
        //System.out.println("largest: " + largest);
        //getMaxArea(heights, 6);
        int[] array = new int[]{1,2,3};
        permute(array);
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

    /**
     * N-Queens II
     *
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that
     * no two queens attack each other.
     *
     * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 4
     * Output: 2
     * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
     * Example 2:
     *
     * Input: n = 1
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= n <= 9
     *
     *
     */
    public int totalNQueens(int n) {
        if (n == 0)
            return 0;
        int[] q = new int[n];
        return track(q, 0);
    }

    private int track(int[] q, int row) {
        if (row == q.length)
            return 1;
        int solutions = 0;
        for (int i = 0; i < q.length; i++) {
            q[row] = i;
            if (isValid(q, row, i)) {
                solutions += track(q, row + 1);
            }
        }
        return solutions;
    }

    private boolean isValid(int[] q, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (q[i] == col || Math.abs(row - i) == Math.abs(col - q[i]))
                return false;
        }
        return true;
    }

    /**
     * Sudoku Solver
     *
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     *
     * A sudoku solution must satisfy all of the following rules:
     *
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * The '.' character indicates empty cells.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
     * Explanation: The input board is shown above and the only valid solution is shown below:
     *
     *
     *
     *
     * Constraints:
     *
     * board.length == 9
     * board[i].length == 9
     * board[i][j] is a digit or '.'.
     * It is guaranteed that the input board has only one solution.
     *
     *
     *
     */

    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }

    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell

                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

    /**
     *
     * Combinations
     *
     * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
     *
     * You may return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 4, k = 2
     * Output:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * Example 2:
     *
     * Input: n = 1, k = 1
     * Output: [[1]]
     *
     *
     * Constraints:
     *
     * 1 <= n <= 20
     * 1 <= k <= n
     *
     *
     *
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        combine(combs, new ArrayList<Integer>(), 1, n, k);
        return combs;
    }

    public void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
        System.out.println("combine: start=" + start + " n=" + n + " k=" + k);
        if(k==0) {
            combs.add(new ArrayList<Integer>(comb));
            return;
        }
        for(int i=start;i<=n;i++) {
            comb.add(i);
            combine(combs, comb, i+1, n, k-1);
            comb.remove(comb.size()-1);
        }
    }

    /**
     *
     * Same Tree
     *
     * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
     *
     * Two binary trees are considered the same if they are structurally identical,
     * and the nodes have the same value.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: p = [1,2,3], q = [1,2,3]
     * Output: true
     * Example 2:
     *
     *
     * Input: p = [1,2], q = [1,null,2]
     * Output: false
     * Example 3:
     *
     *
     * Input: p = [1,2,1], q = [1,1,2]
     * Output: false
     *
     *
     * Constraints:
     *
     * The number of nodes in both trees is in the range [0, 100].
     * -104 <= Node.val <= 104
     *
     *
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        if (p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     *
     * Generate Parentheses
     *
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 3
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     * Example 2:
     *
     * Input: n = 1
     * Output: ["()"]
     *
     *
     * Constraints:
     *
     * 1 <= n <= 8
     *
     *
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max) {

        //System.out.println("str=" + str + " open=" + open + " close=" + close + " max=" + max);
        System.out.println("backtrace(" + str + "," + open + "," + close + "," + max + ")");

        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, str + ")", open, close + 1, max);

    }

    /**
     *
     * Largest Rectangle in Histogram
     *
     * Given an array of integers heights representing the histogram's bar height where the width of
     * each bar is 1, return the area of the largest rectangle in the histogram.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: heights = [2,1,5,6,2,3]
     * Output: 10
     * Explanation: The above is a histogram where width of each bar is 1.
     * The largest rectangle is shown in the red area, which has an area = 10 units.
     * Example 2:
     *
     *
     * Input: heights = [2,4]
     * Output: 4
     *
     *
     * Constraints:
     *
     * 1 <= heights.length <= 105
     * 0 <= heights[i] <= 104
     *
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[heights.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[heights.length]; // idx of the first bar the right that is lower than current
        lessFromRight[heights.length - 1] = heights.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;

            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }

    /**
     *
     * Permutations
     *
     * Given an array nums of distinct integers, return all the possible permutations.
     * You can return the answer in any order.
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * Example 2:
     *
     * Input: nums = [0,1]
     * Output: [[0,1],[1,0]]
     * Example 3:
     *
     * Input: nums = [1]
     * Output: [[1]]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * All the integers of nums are unique.
     *
     *
     *
     */

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null)
            return null;

        List<List<Integer>> result = new ArrayList<>();
        permute(result, new ArrayList<>(), nums);
        return result;
    }

    private void permute(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i]))
                continue;
            list.add(nums[i]);
            permute(res, list, nums);
            list.remove(list.size() - 1);
        }
    }


    /**
     *
     * Letter Combinations of a Phone Number
     *
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     * Return the answer in any order.
     *
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     *
     *
     *
     *
     *
     * Example 1:
     *
     * Input: digits = "23"
     * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * Example 2:
     *
     * Input: digits = ""
     * Output: []
     * Example 3:
     *
     * Input: digits = "2"
     * Output: ["a","b","c"]
     *
     *
     * Constraints:
     *
     * 0 <= digits.length <= 4
     * digits[i] is a digit in the range ['2', '9'].
     *
     */

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i = 0; i < digits.length(); i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length() == i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }

    /**
     *
     * The Skyline Problem
     *
     * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
     * Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
     *
     * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
     *
     * lefti is the x coordinate of the left edge of the ith building.
     * righti is the x coordinate of the right edge of the ith building.
     * heighti is the height of the ith building.
     * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
     *
     * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...].
     * Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list,
     * which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends.
     * Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
     *
     * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],
     * [11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],
     * [4 5],[12 7],...]
     *
     *
     *
     * Example 1:
     *
     *
     * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
     * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
     * Explanation:
     * Figure A shows the buildings of the input.
     * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
     * Example 2:
     *
     * Input: buildings = [[0,2,3],[2,5,3]]
     * Output: [[0,3],[5,0]]
     *
     *
     * Constraints:
     *
     * 1 <= buildings.length <= 104
     * 0 <= lefti < righti <= 231 - 1
     * 1 <= heighti <= 231 - 1
     * buildings is sorted by lefti in non-decreasing order.
     *
     *
     */

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings.length == 0)
            return new LinkedList<List<Integer>>();
        return recurSkyline(buildings, 0, buildings.length - 1);
    }

    private LinkedList<List<Integer>> recurSkyline(int[][] buildings, int p, int q) {
        if (p < q) {
            int mid = p + (q - p) / 2;
            return merge(recurSkyline(buildings, p, mid),
                    recurSkyline(buildings, mid + 1, q));
        } else {
            LinkedList<List<Integer>> rs = new LinkedList<List<Integer>>();

            List<Integer> l1 = new ArrayList<>();
            l1.add(buildings[p][0]);
            l1.add(buildings[p][2]);


            List<Integer> l2 = new ArrayList<>();
            l2.add(buildings[p][1]);
            l2.add(0);

            rs.add(l1);
            rs.add(l2);

            return rs;
        }
    }

    private LinkedList<List<Integer>> merge(LinkedList<List<Integer>> l1, LinkedList<List<Integer>> l2) {
        LinkedList<List<Integer>> rs = new LinkedList<List<Integer>>();
        int h1 = 0, h2 = 0;
        while (l1.size() > 0 && l2.size() > 0) {
            int x = 0, h = 0;
            if (l1.getFirst().get(0) < l2.getFirst().get(0)) {
                x = l1.getFirst().get(0);
                h1 = l1.getFirst().get(1);
                h = Math.max(h1, h2);
                l1.removeFirst();
            } else if (l1.getFirst().get(0) > l2.getFirst().get(0)) {
                x = l2.getFirst().get(0);
                h2 = l2.getFirst().get(1);
                h = Math.max(h1, h2);
                l2.removeFirst();
            } else {
                x = l1.getFirst().get(0);
                h1 = l1.getFirst().get(1);
                h2 = l2.getFirst().get(1);
                h = Math.max(h1, h2);
                l1.removeFirst();
                l2.removeFirst();
            }
            if (rs.size() == 0 || h != rs.getLast().get(1)) {

                List<Integer> list = new ArrayList<>();
                list.add(x);
                list.add(h);

                rs.add(list);
            }
        }
        rs.addAll(l1);
        rs.addAll(l2);
        return rs;
    }
}
