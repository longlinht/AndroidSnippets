package com.snippets.tao.androidsnippets.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao He on 2021/6/8.
 * hetaoof@gmail.com
 */
public class BinaryTreeQuestions {

    public void test() {

    }


    /**
     *
     * Given the root of a binary tree, return the preorder traversal of its nodes' values.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,null,2,3]
     * Output: [1,2,3]
     * Example 2:
     *
     * Input: root = []
     * Output: []
     * Example 3:
     *
     * Input: root = [1]
     * Output: [1]
     * Example 4:
     *
     *
     * Input: root = [1,2]
     * Output: [1,2]
     * Example 5:
     *
     *
     * Input: root = [1,null,2]
     * Output: [1,2]
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     *
     *
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     *
     */

     public static class TreeNode {
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

    private List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {

         if(root == null)
             return list;

         list.add(root.val);
         preorderTraversal(root.left);
         preorderTraversal(root.right);

         return list;
    }

    /**
     * Given the root of a binary tree, return the inorder traversal of its nodes' values.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,null,2,3]
     * Output: [1,3,2]
     * Example 2:
     *
     * Input: root = []
     * Output: []
     * Example 3:
     *
     * Input: root = [1]
     * Output: [1]
     * Example 4:
     *
     *
     * Input: root = [1,2]
     * Output: [2,1]
     * Example 5:
     *
     *
     * Input: root = [1,null,2]
     * Output: [1,2]
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     *
     *
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     *
     *
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return list;

        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);

        return list;
    }

    /**
     *
     * Given the root of a binary tree, return the postorder traversal of its nodes' values.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,null,2,3]
     * Output: [3,2,1]
     * Example 2:
     *
     * Input: root = []
     * Output: []
     * Example 3:
     *
     * Input: root = [1]
     * Output: [1]
     * Example 4:
     *
     *
     * Input: root = [1,2]
     * Output: [2,1]
     * Example 5:
     *
     *
     * Input: root = [1,null,2]
     * Output: [2,1]
     *
     *
     * Constraints:
     *
     * The number of the nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     *
     *
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     *
     */

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)
            return list;

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);

        return list;
    }

    /**
     * Given the root of a binary tree, return the level order traversal of its nodes' values.
     * (i.e., from left to right, level by level).
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[9,20],[15,7]]
     * Example 2:
     *
     * Input: root = [1]
     * Output: [[1]]
     * Example 3:
     *
     * Input: root = []
     * Output: []
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 2000].
     * -1000 <= Node.val <= 1000
     *
     *
     */

    private int height(TreeNode node) {
        if(node == null)
            return 0;

        int lHeight = height(node.left);
        int rHeight = height(node.right);

        if (lHeight > rHeight) {
            return lHeight + 1;
        } else {
            return rHeight + 1;
        }
    }

    private void traverseCurrentLevel(TreeNode root, int level, List<Integer> list) {
        if (root == null)
            return;

        if (level == 1) {
            list.add(root.val);
        } else if (level > 1) {
            traverseCurrentLevel(root.left, level - 1, list);
            traverseCurrentLevel(root.right, level - 1, list);
        }
    }

    List<List<Integer>> levelNodesList = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {

        int h = height(root);
        for (int i = 1; i <= h; i++) {
            List<Integer> list = new ArrayList<>();
            traverseCurrentLevel(root, i, list);
            levelNodesList.add(list);
        }

        return levelNodesList;
    }
}
