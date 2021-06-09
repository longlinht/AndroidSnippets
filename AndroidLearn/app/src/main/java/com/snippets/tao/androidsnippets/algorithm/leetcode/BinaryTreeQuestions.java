package com.snippets.tao.androidsnippets.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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

    /**
     * Given the root of a binary tree, return its maximum depth.
     *
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node
     * down to the farthest leaf node.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: 3
     * Example 2:
     *
     * Input: root = [1,null,2]
     * Output: 2
     * Example 3:
     *
     * Input: root = []
     * Output: 0
     * Example 4:
     *
     * Input: root = [0]
     * Output: 1
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 104].
     * -100 <= Node.val <= 100
     *
     *
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int leftAns = maxDepth(root.left);
        int rightAns = maxDepth(root.right);

        return Math.max(leftAns+1, rightAns+1);
    }

    /**
     *
     * Symmetric Tree
     *
     * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,2,2,3,4,4,3]
     * Output: true
     *
     *
     * Example 2:
     *
     * Input: root = [1,2,2,null,3,null,3]
     * Output: false
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 1000].
     * -100 <= Node.val <= 100
     *
     *
     * Follow up: Could you solve it both recursively and iteratively?
     *
     *
     */

    // Recursive version
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right){
        if(left == null || right == null)
            return left == right;

        if(left.val != right.val)
            return false;

        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    // Iterative version
    public boolean isSymmetricInterative(TreeNode root) {
        if(root==null)  return true;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode left, right;
        if(root.left!=null){
            if(root.right==null) return false;
            stack.push(root.left);
            stack.push(root.right);
        }
        else if(root.right!=null){
            return false;
        }

        while(!stack.empty()){
            if(stack.size()%2!=0)   return false;
            right = stack.pop();
            left = stack.pop();
            if(right.val!=left.val) return false;

            if(left.left!=null){
                if(right.right==null)   return false;
                stack.push(left.left);
                stack.push(right.right);
            }
            else if(right.right!=null){
                return false;
            }

            if(left.right!=null){
                if(right.left==null)   return false;
                stack.push(left.right);
                stack.push(right.left);
            }
            else if(right.left!=null){
                return false;
            }
        }

        return true;
    }

    /**
     * Path Sum
     *
     * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf
     * path such that adding up all the values along the path equals targetSum.
     *
     * A leaf is a node with no children.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * Output: true
     * Example 2:
     *
     *
     * Input: root = [1,2,3], targetSum = 5
     * Output: false
     * Example 3:
     *
     * Input: root = [1,2], targetSum = 0
     * Output: false
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 5000].
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     *
     *
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

        if(root.left == null && root.right == null && targetSum - root.val == 0)
            return true;

        boolean leftHas = hasPathSum(root.left, targetSum - root.val);
        boolean rightHas = hasPathSum(root.right, targetSum - root.val);

        return leftHas || rightHas;
    }

    /**
     *
     * Construct Binary Tree from Inorder and Postorder Traversal
     *
     * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary
     * tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
     *
     *
     * Example 1:
     *
     *
     * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * Output: [3,9,20,null,null,15,7]
     * Example 2:
     *
     * Input: inorder = [-1], postorder = [-1]
     * Output: [-1]
     *
     *
     * Constraints:
     *
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * -3000 <= inorder[i], postorder[i] <= 3000
     * inorder and postorder consist of unique values.
     * Each value of postorder also appears in inorder.
     * inorder is guaranteed to be the inorder traversal of the tree.
     * postorder is guaranteed to be the postorder traversal of the tree.
     *
     */
    public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        for (int i = 0;i < inorder.length; ++i)
            hm.put(inorder[i], i);
        return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0,
                postorder.length-1,hm);
    }

    private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
                                     HashMap<Integer,Integer> hm){
        if (ps>pe || is>ie) return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int ri = hm.get(postorder[pe]);
        TreeNode leftchild = buildTreePostIn(inorder, is, ri-1, postorder, ps, ps+ri-is-1, hm);
        TreeNode rightchild = buildTreePostIn(inorder,ri+1, ie, postorder, ps+ri-is, pe-1, hm);
        root.left = leftchild;
        root.right = rightchild;
        return root;
    }

    /**
     * Construct Binary Tree from Preorder and Inorder Traversal
     *
     * Solution
     * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     * Example 2:
     *
     * Input: preorder = [-1], inorder = [-1]
     * Output: [-1]
     *
     *
     * Constraints:
     *
     * 1 <= preorder.length <= 3000
     * inorder.length == preorder.length
     * -3000 <= preorder[i], inorder[i] <= 3000
     * preorder and inorder consist of unique values.
     * Each value of inorder also appears in preorder.
     * preorder is guaranteed to be the preorder traversal of the tree.
     * inorder is guaranteed to be the inorder traversal of the tree.
     *
     *
     */
    public TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length)
            return null;
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        for (int i = 0;i < inorder.length; ++i)
            hm.put(inorder[i], i);
        return buildTreePreIn(inorder, 0, inorder.length-1, preorder, 0,
                preorder.length-1,hm);
    }

    private TreeNode buildTreePreIn(int[] inorder, int is, int ie, int[] preorder, int ps, int pe,
                                     HashMap<Integer,Integer> hm){
        if (ps>pe || is>ie) return null;
        TreeNode root = new TreeNode(preorder[ps]);
        int ri = hm.get(preorder[ps]);
        TreeNode leftchild = buildTreePreIn(inorder, is, ri-1, preorder, ps + 1, ps+ri-is + 1, hm);
        TreeNode rightchild = buildTreePreIn(inorder,ri+1, ie, preorder, ps+ri-is + 1, pe, hm);
        root.left = leftchild;
        root.right = rightchild;
        return root;
    }

    /**
     * Populating Next Right Pointers in Each Node
     *
     * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
     * The binary tree has the following definition:
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node,
     * the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     *
     *
     *
     * Follow up:
     *
     * You may only use constant extra space.
     * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
     *
     *
     * Example 1:
     *
     *
     *
     * Input: root = [1,2,3,4,5,6,7]
     * Output: [1,#,2,3,#,4,5,6,7,#]
     * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next
     * pointer to point to its next right node, just like in Figure B.
     * The serialized output is in level order as connected by the next pointers, with '#' signifying the end
     * of each level.
     *
     *
     * Constraints:
     *
     * The number of nodes in the given tree is less than 4096.
     * -1000 <= node.val <= 1000
     *
     *
     */

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if (root == null || root.left == null) {
            return root;
        }

        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);

        return root;
    }

    /**
     * Populating Next Right Pointers in Each Node II
     *
     * Given a binary tree
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node,
     * the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     *
     *
     *
     * Follow up:
     *
     * You may only use constant extra space.
     * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
     *
     *
     * Example 1:
     *
     *
     *
     * Input: root = [1,2,3,4,5,null,7]
     * Output: [1,#,2,3,#,4,5,7,#]
     * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to
     * point to its next right node, just like in Figure B. The serialized output
     * is in level order as connected by the next pointers, with '#' signifying the end of each level.
     *
     *
     * Constraints:
     *
     * The number of nodes in the given tree is less than 6000.
     * -100 <= node.val <= 100
     *
     *
     */

    public Node connect2(Node root) {
        if (root == null) return null;

        if (root.left != null) { // update left next
            if (root.right != null) root.left.next = root.right; // if right child exists - simple connect left.next to right
            else root.left.next = findNext(root); // if not - scan parent next node until we find the first left or right child
        }
        if (root.right != null) { // update right next
            root.right.next = findNext(root);
        }

        connect2(root.right); // update the right nodes first
        connect2(root.left);
        return root;
    }

    private Node findNext(Node root) { // get parent node
        while (root.next != null) { // scan all next parent nodes until we find the first left or right child
            root = root.next;
            if (root.left != null) return root.left;
            if (root.right != null) return root.right;
        }
        return null;
    }

    /**
     *
     * Lowest Common Ancestor of a Binary Tree
     *
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
     * two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a
     * node to be a descendant of itself).”
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * Output: 3
     * Explanation: The LCA of nodes 5 and 1 is 3.
     * Example 2:
     *
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * Output: 5
     * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the
     * LCA definition.
     * Example 3:
     *
     * Input: root = [1,2], p = 1, q = 2
     * Output: 1
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 105].
     * -109 <= Node.val <= 109
     * All Node.val are unique.
     * p != q
     * p and q will exist in the tree.
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    /**
     * Serialize and Deserialize Binary Tree
     *
     * Serialization is the process of converting a data structure or object into a sequence of bits so
     * that it can be stored in a file or memory buffer, or transmitted across a network connection link
     * to be reconstructed later in the same or another computer environment.
     *
     * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
     * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can
     * be serialized to a string and this string can be deserialized to the original tree structure.
     *
     * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
     * You do not necessarily need to follow this format, so please be creative and come up with different
     * approaches yourself.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,2,3,null,null,4,5]
     * Output: [1,2,3,null,null,4,5]
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
     * Input: root = [1,2]
     * Output: [1,2]
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 104].
     * -1000 <= Node.val <= 1000
     *
     *
     */
    public class Codec {

        private static final String spliter = ",";
        private static final String NN = "X";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            buildString(root, sb);
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NN).append(spliter);
            } else {
                sb.append(node.val).append(spliter);
                buildString(node.left, sb);
                buildString(node.right,sb);
            }
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> nodes = new LinkedList<>();
            nodes.addAll(Arrays.asList(data.split(spliter)));
            return buildTree(nodes);
        }

        private TreeNode buildTree(Deque<String> nodes) {
            String val = nodes.remove();
            if (val.equals(NN)) return null;
            else {
                TreeNode node = new TreeNode(Integer.valueOf(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
    }
}
