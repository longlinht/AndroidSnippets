package com.snippets.tao.androidsnippets.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Tao He on 2021/6/9.
 * hetaoof@gmail.com
 */

public class QueueStackQuestions {

    public void test() {
        // 1. Initialize a queue.
        Queue<Integer> q = new LinkedList();
        // 2. Get the first element - return null if queue is empty.
        System.out.println("The first element is: " + q.peek());
        // 3. Push new element.
        q.offer(5);
        q.offer(13);
        q.offer(8);
        q.offer(6);
        // 4. Pop an element.
        q.poll();
        // 5. Get the first element.
        System.out.println("The first element is: " + q.peek());
        // 7. Get the size of the queue.
        System.out.println("The size is: " + q.size());

        int M[][] = new int[][] { { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };


        char[][] mx = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};


        int mat[][] = {
                { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };

        System.out.println("count: " + numIslands(mx));



        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";

        String ss = "0000";
        char[] chars = ss.toCharArray();

        int i = '0';
        System.out.println("0 int value=" + i);

        int val = (chars[0] - 1 - '0') % 10;
        System.out.println("val=" + val);

        chars[0] = (char)((chars[0] - 1 - '0') % 10 + '0');
        System.out.println(chars);

        //System.out.println("open lock need: " + openLock(deadends, target));;
        System.out.println("Num of squares: " + numSquares(12));

        System.out.println("is valid: " + isValid("}"));
    }


    /**
     *
     * Design Circular Queue
     *
     * Design your implementation of the circular queue. The circular queue is a linear data structure in
     * which the operations are performed based on FIFO (First In First Out) principle and the last position
     * is connected back to the first position to make a circle. It is also called "Ring Buffer".
     *
     * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
     * In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a
     * space in front of the queue. But using the circular queue, we can use the space to store new values.
     *
     * Implementation the MyCircularQueue class:
     *
     * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
     * int Front() Gets the front item from the queue. If the queue is empty, return -1.
     * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
     * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
     * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
     * boolean isEmpty() Checks whether the circular queue is empty or not.
     * boolean isFull() Checks whether the circular queue is full or not.
     * You must solve the problem without using the built-in queue data structure in your programming language.
     *
     *
     *
     * Example 1:
     *
     * Input
     * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
     * [[3], [1], [2], [3], [4], [], [], [], [4], []]
     * Output
     * [null, true, true, true, false, 3, true, true, true, 4]
     *
     * Explanation
     * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
     * myCircularQueue.enQueue(1); // return True
     * myCircularQueue.enQueue(2); // return True
     * myCircularQueue.enQueue(3); // return True
     * myCircularQueue.enQueue(4); // return False
     * myCircularQueue.Rear();     // return 3
     * myCircularQueue.isFull();   // return True
     * myCircularQueue.deQueue();  // return True
     * myCircularQueue.enQueue(4); // return True
     * myCircularQueue.Rear();     // return 4
     *
     *
     * Constraints:
     *
     * 1 <= k <= 1000
     * 0 <= value <= 1000
     * At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
     *
     *
     *
     */

    class MyCircularQueue {


        private int[] data;
        private int head;
        private int tail;
        private int size;

        /**
         * Initialize your data structure here. Set the size of the queue to be k.
         */
        public MyCircularQueue(int k) {
            data = new int[k];
            head = -1;
            tail = -1;
            size = k;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is successful.
         */
        public boolean enQueue(int value) {
            if (isFull() == true) {
                return false;
            }
            if (isEmpty() == true) {
                head = 0;
            }
            tail = (tail + 1) % size;
            data[tail] = value;
            return true;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is successful.
         */
        public boolean deQueue() {
            if (isEmpty() == true) {
                return false;
            }
            if (head == tail) {
                head = -1;
                tail = -1;
                return true;
            }
            head = (head + 1) % size;
            return true;
        }

        /**
         * Get the front item from the queue.
         */
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return data[head];
        }

        /**
         * Get the last item from the queue.
         */
        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return data[tail];
        }

        /**
         * Checks whether the circular queue is empty or not.
         */
        public boolean isEmpty() {
            return head == -1;
        }

        /**
         * Checks whether the circular queue is full or not.
         */
        public boolean isFull() {
            return ((tail + 1) % size) == head;
        }

    }


    /**
     *
     * Number of Islands
     *
     * Solution
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     *
     *
     *
     * Example 1:
     *
     * Input: grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * Output: 1
     * Example 2:
     *
     * Input: grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * Output: 3
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] is '0' or '1'.
     *
     *
     */

    /*

    // R x C matrix
    private int R = 5;
    private int C = 5 ;
    private class pair
    {
        int first, second;
        public pair(int first, int second)
        {
            this.first = first;
            this.second = second;
        }
    }

    // A function to check if a given cell
// (u, v) can be included in DFS
    private boolean isSafe(char mat[][], int i, int j,
                           boolean vis[][])
    {
        return (i >= 0) && (i < R) &&
                (j >= 0) && (j < C) &&
                (mat[i][j]== '1' && !vis[i][j]);
    }

    private void BFS(char mat[][], boolean vis[][],
                     int si, int sj)
    {



        // These arrays are used to get row and
        // column numbers of 8 neighbours of
        // a given cell
        int row[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int col[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

        // Simple BFS first step, we enqueue
        // source and mark it as visited
        Queue<pair> q = new LinkedList<pair>();

        System.out.println("add to queue pair: si=" + si + " sj=" + sj);
        q.add(new pair(si, sj));
        vis[si][sj] = true;

        // Next step of BFS. We take out
        // items one by one from queue and
        // enqueue their unvisited adjacent
        while (!q.isEmpty())
        {

            int i = q.peek().first;
            int j = q.peek().second;
            q.remove();

            System.out.println("remove from queue pair: si=" + i + " sj=" + j);

            // Go through all 8 adjacent
            for (int k = 0; k < 8; k++)
            {
                if (isSafe(mat, i + row[k],
                        j + col[k], vis))
                {
                    vis[i + row[k]][j + col[k]] = true;

                    System.out.println("set visited: vis[" + (i + row[k]) + "][" + (j + col[k])+ "]");

                    q.add(new pair(i + row[k], j + col[k]));
                }
            }
        }
    }

    // This function returns number islands (connected
    // components) in a graph. It simply works as
    // BFS for disconnected graph and returns count
    // of BFS calls.
    private int countIslands(char mat[][])
    {
        if(mat == null || mat.length == 0)
            return 0;

        R = mat.length;
        C = mat[0].length;
        // Mark all cells as not visited
        boolean [][]vis = new boolean[R][C];

        // Call BFS for every unvisited vertex
        // Whenever we see an univisted vertex,
        // we increment res (number of islands)
        // also.
        int res = 0;
        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                if (mat[i][j]== '1' && !vis[i][j])
                {
                    System.out.println("countIslands: m[" + i + "][" + j + "]");

                    BFS(mat, vis, i, j);
                    res++;
                }
            }
        }
        return res;
    }
     */


    private int n;
    private int m;

    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }

    /**
     *
     * Open the Lock
     *
     * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3',
     * '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn
     * '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
     *
     * The lock initially starts at '0000', a string representing the state of the 4 wheels.
     *
     * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
     * the wheels of the lock will stop turning and you will be unable to open it.
     *
     * Given a target representing the value of the wheels that will unlock the lock,
     * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
     *
     *
     *
     * Example 1:
     *
     * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * Output: 6
     * Explanation:
     * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
     * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
     * because the wheels of the lock become stuck after the display becomes the dead end "0102".
     * Example 2:
     *
     * Input: deadends = ["8888"], target = "0009"
     * Output: 1
     * Explanation:
     * We can turn the last wheel in reverse to move from "0000" -> "0009".
     * Example 3:
     *
     * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * Output: -1
     * Explanation:
     * We can't reach the target without getting stuck.
     * Example 4:
     *
     * Input: deadends = ["0000"], target = "8888"
     * Output: -1
     *
     *
     * Constraints:
     *
     * 1 <= deadends.length <= 500
     * deadends[i].length == 4
     * target.length == 4
     * target will not be in the list deadends.
     * target and deadends[i] consist of digits only.
     *
     *
     */
    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains(target) || deadSet.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) {
                    return step;
                }
                for (int pos = 0; pos < 4; pos++) {
                    char[] chars = curr.toCharArray();
                    // (chars[pos] + 1 + 10 - '0') % 10 = (chars[pos] + 11 - '0') % 10
                    chars[pos] = (char)((chars[pos] + 11 - '0') % 10 + '0');
                    String nextString = String.valueOf(chars);
                    System.out.println("nextString: " + nextString);
                    if (!visited.contains(nextString) && !deadSet.contains(nextString)) {
                        queue.offer(nextString);
                        visited.add(nextString);
                    }
                    // (chars[pos] - 2 + 10 - '0') % 10 = (chars[pos] + 8 - '0') % 10
                    chars[pos] = (char)((chars[pos] + 8 - '0') % 10 + '0');
                    nextString = String.valueOf(chars);
                    System.out.println("nextString: " + nextString);
                    if (!visited.contains(nextString) && !deadSet.contains(nextString)) {
                        queue.offer(nextString);
                        visited.add(nextString);
                    }
                }
            }
            step++;
        }

        return -1;
    }


    /*
    private static List<String> getSuccessors(String str) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            res.add(str.substring(0, i) + (str.charAt(i) == '0' ? 9 :  str.charAt(i) - '0' - 1) + str.substring(i+1));
            res.add(str.substring(0, i) + (str.charAt(i) == '9' ? 0 :  str.charAt(i) - '0' + 1) + str.substring(i+1));
        }
        return res;
    }

    public static int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        int depth = -1;
        q.addAll(Arrays.asList("0000"));
        while(!q.isEmpty()) {
            depth++;
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String node = q.poll();
                if(node.equals(target))
                    return depth;
                if(visited.contains(node))
                    continue;
                visited.add(node);
                q.addAll(getSuccessors(node));
            }
        }
        return -1;
    }
     */

    /**
     *
     * Perfect Squares
     *
     * Given an integer n, return the least number of perfect square numbers that sum to n.
     *
     * A perfect square is an integer that is the square of an integer; in other words,
     * it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect
     * squares while 3 and 11 are not.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 12
     * Output: 3
     * Explanation: 12 = 4 + 4 + 4.
     * Example 2:
     *
     * Input: n = 13
     * Output: 2
     * Explanation: 13 = 4 + 9.
     *
     *
     * Constraints:
     *
     * 1 <= n <= 104
     *
     */
    public int numSquares(int n) {

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        if (n > 0) {
            queue.offer(n);
        }

        int level = 0;
        while(!queue.isEmpty()) {
            level++;

            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int val = queue.poll();
                if (visited.contains(val))
                    continue;

                for (int j = 1; j <= Math.sqrt(val); j++) {
                    if (val - (j * j) == 0) return level;
                    queue.offer(val - (j*j));
                }
            }
        }
        return level;
    }


    /**
     * Min Stack
     *
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     *
     * Implement the MinStack class:
     *
     * MinStack() initializes the stack object.
     * void push(val) pushes the element val onto the stack.
     * void pop() removes the element on the top of the stack.
     * int top() gets the top element of the stack.
     * int getMin() retrieves the minimum element in the stack.
     *
     *
     * Example 1:
     *
     * Input
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     *
     * Output
     * [null,null,null,null,-3,null,0,-2]
     *
     * Explanation
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin(); // return -3
     * minStack.pop();
     * minStack.top();    // return 0
     * minStack.getMin(); // return -2
     *
     *
     * Constraints:
     *
     * -231 <= val <= 231 - 1
     * Methods pop, top and getMin operations will always be called on non-empty stacks.
     * At most 3 * 104 calls will be made to push, pop, top, and getMin.
     *
     */
    class MinStack {

        private Node head;

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int val) {
            if (head == null) {
                head = new Node(val, val, null);
            } else {
                head = new Node(val, Math.min(val, head.min), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        class Node {
            public int val;
            public int min;
            public Node next;

            public Node(int v, int m, Node n) {
                val = v;
                min = m;
                next = n;
            }
        }
    }


    /**
     *
     * Valid Parentheses
     *
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     *
     *
     * Example 1:
     *
     * Input: s = "()"
     * Output: true
     * Example 2:
     *
     * Input: s = "()[]{}"
     * Output: true
     * Example 3:
     *
     * Input: s = "(]"
     * Output: false
     * Example 4:
     *
     * Input: s = "([)]"
     * Output: false
     * Example 5:
     *
     * Input: s = "{[]}"
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 104
     * s consists of parentheses only '()[]{}'.
     *
     *
     *
     */
    public boolean isValid(String s) {

        if (s == null || s.length() == 0)
            return false;

        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            switch (c) {
                case '(' :
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                   if (!stack.isEmpty() && stack.peek() == '(') {
                       stack.pop();
                   } else {
                       return false;
                   }
                   break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }

        return stack.isEmpty();
    }

    /**
     *
     * Daily Temperatures
     * Given an array of integers temperatures represents the daily temperatures,
     * return an array answer such that answer[i] is the number of days you have to wait
     * after the ith day to get a warmer temperature. If there is no future day for which this is possible,
     * keep answer[i] == 0 instead.
     *
     *
     * Example 1:
     *
     * Input: temperatures = [73,74,75,71,69,72,76,73]
     * Output: [1,1,4,2,1,1,0,0]
     * Example 2:
     *
     * Input: temperatures = [30,40,50,60]
     * Output: [1,1,1,0]
     * Example 3:
     *
     * Input: temperatures = [30,60,90]
     * Output: [1,1,0]
     *
     *
     * Constraints:
     *
     * 1 <= temperatures.length <= 105
     * 30 <= temperatures[i] <= 100
     *
     *
     *
     */
    public int[] dailyTemperatures(int[] temperatures) {
        if(temperatures == null || temperatures.length == 0)
            return null;

        int n = temperatures.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[i] < temperatures[j]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

    /**
     *
     *
     Evaluate Reverse Polish Notation

     Evaluate the value of an arithmetic expression in Reverse Polish Notation.

     Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

     Note that division between two integers should truncate toward zero.

     It is guaranteed that the given RPN expression is always valid. That means the expression would
     always evaluate to a result, and there will not be any division by zero operation.



     Example 1:

     Input: tokens = ["2","1","+","3","*"]
     Output: 9
     Explanation: ((2 + 1) * 3) = 9
     Example 2:

     Input: tokens = ["4","13","5","/","+"]
     Output: 6
     Explanation: (4 + (13 / 5)) = 6
     Example 3:

     Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     Output: 22
     Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     = ((10 * (6 / (12 * -11))) + 17) + 5
     = ((10 * (6 / -132)) + 17) + 5
     = ((10 * 0) + 17) + 5
     = (0 + 17) + 5
     = 17 + 5
     = 22


     Constraints:

     1 <= tokens.length <= 104
     tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
     *
     *
     *
     */

    public int evalRPN(String[] tokens) {
        int a,b;
        Stack<Integer> stack = new Stack<Integer>();
        for (String s : tokens) {
            if(s.equals("+")) {
                stack.add(stack.pop()+stack.pop());
            }
            else if(s.equals("/")) {
                b = stack.pop();
                a = stack.pop();
                stack.add(a / b);
            }
            else if(s.equals("*")) {
                stack.add(stack.pop() * stack.pop());
            }
            else if(s.equals("-")) {
                b = stack.pop();
                a = stack.pop();
                stack.add(a - b);
            }
            else {
                stack.add(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    /**
     * Clone Graph
     *
     * Given a reference of a node in a connected undirected graph.
     *
     * Return a deep copy (clone) of the graph.
     *
     * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
     *
     * class Node {
     *     public int val;
     *     public List<Node> neighbors;
     * }
     *
     *
     * Test case format:
     *
     * For simplicity, each node's value is the same as the node's index (1-indexed). For example,
     * the first node with val == 1,
     * the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
     *
     * An adjacency list is a collection of unordered lists used to represent a finite graph.
     * Each list describes the set of neighbors of a node in the graph.
     *
     * The given node will always be the first node with val = 1.
     * You must return the copy of the given node as a reference to the cloned graph.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
     * Output: [[2,4],[1,3],[2,4],[1,3]]
     * Explanation: There are 4 nodes in the graph.
     * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
     * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
     * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
     * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
     *
     * Example 2:
     *
     * Input: adjList = [[]]
     * Output: [[]]
     * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
     *
     * Example 3:
     *
     * Input: adjList = []
     * Output: []
     * Explanation: This an empty graph, it does not have any nodes.
     *
     * Example 4:
     *
     * Input: adjList = [[2],[1]]
     * Output: [[2],[1]]
     *
     *
     * Constraints:
     *
     * The number of nodes in the graph is in the range [0, 100].
     * 1 <= Node.val <= 100
     * Node.val is unique for each node.
     * There are no repeated edges and no self-loops in the graph.
     * The Graph is connected and all nodes can be visited starting from the given node.
     *
     */

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private Map<Integer, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        return clone(node);
    }

    public Node clone(Node node) {
        if (node == null)
            return node;

        if (map.get(node.val) != null) {
            return map.get(node.val);
        }

        Node newNode = new Node(node.val);
        map.put(node.val, newNode);

        List<Node> neighbors = node.neighbors;
        for (Node n : neighbors) {
            newNode.neighbors.add(clone(n));
        }
        return newNode;
    }


    /**
     *
     * Target Sum
     *
     * You are given an integer array nums and an integer target.
     *
     * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each
     * integer in nums and then concatenate all the integers.
     *
     * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them
     * to build the expression "+2-1".
     * Return the number of different expressions that you can build, which evaluates to target.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,1,1], target = 3
     * Output: 5
     * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * Example 2:
     *
     * Input: nums = [1], target = 1
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 1000
     *
     *
     */

    int result = 0;

    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return result;

        int n = nums.length;
        int[] sums = new int[n];
        sums[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            sums[i] = sums[i + 1] + nums[i];

        helper(nums, sums, S, 0);
        return result;
    }
    public void helper(int[] nums, int[] sums, int target, int pos){
        if(pos == nums.length){
            if(target == 0) result++;
            return;
        }

        if (sums[pos] < Math.abs(target)) return;

        helper(nums, sums, target + nums[pos], pos + 1);
        helper(nums, sums, target - nums[pos], pos + 1);
    }

    /**
     * Implement Queue using Stacks
     *
     * Implement a first in first out (FIFO) queue using only two stacks.
     * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
     *
     * Implement the MyQueue class:
     *
     * void push(int x) Pushes element x to the back of the queue.
     * int pop() Removes the element from the front of the queue and returns it.
     * int peek() Returns the element at the front of the queue.
     * boolean empty() Returns true if the queue is empty, false otherwise.
     * Notes:
     *
     * You must use only standard operations of a stack, which means only push to top, peek/pop from top,
     * size, and is empty operations are valid.
     * Depending on your language, the stack may not be supported natively. You may simulate a stack using a
     * list or deque (double-ended queue) as long as you use only a stack's standard operations.
     * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity?
     * In other words, performing n operations will take overall O(n) time even if one of those operations
     * may take longer.
     *
     *
     *
     * Example 1:
     *
     * Input
     * ["MyQueue", "push", "push", "peek", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * Output
     * [null, null, null, 1, 1, false]
     *
     * Explanation
     * MyQueue myQueue = new MyQueue();
     * myQueue.push(1); // queue is: [1]
     * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
     * myQueue.peek(); // return 1
     * myQueue.pop(); // return 1, queue is [2]
     * myQueue.empty(); // return false
     *
     *
     * Constraints:
     *
     * 1 <= x <= 9
     * At most 100 calls will be made to push, pop, peek, and empty.
     * All the calls to pop and peek are valid.
     *
     */
    class MyQueue {

        private Stack<Integer> queue;
        /** Initialize your data structure here. */
        public MyQueue() {
            queue = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
           Stack<Integer> temp = new Stack<>();
           while(!queue.isEmpty()) {
               temp.push(queue.pop());
           }

           queue.push(x);
           while (!temp.isEmpty()) {
               queue.push(temp.pop());
           }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return queue.pop();
        }

        /** Get the front element. */
        public int peek() {
            return queue.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     *
     * Implement Stack using Queues
     *
     * Implement a last in first out (LIFO) stack using only two queues.
     * The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).
     *
     * Implement the MyStack class:
     *
     * void push(int x) Pushes element x to the top of the stack.
     * int pop() Removes the element on the top of the stack and returns it.
     * int top() Returns the element on the top of the stack.
     * boolean empty() Returns true if the stack is empty, false otherwise.
     * Notes:
     *
     * You must use only standard operations of a queue, which means only push to back, peek/pop from front,
     * size, and is empty operations are valid.
     * Depending on your language, the queue may not be supported natively.
     * You may simulate a queue using a list or deque (double-ended queue),
     * as long as you use only a queue's standard operations.
     *
     *
     * Example 1:
     *
     * Input
     * ["MyStack", "push", "push", "top", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * Output
     * [null, null, null, 2, 2, false]
     *
     * Explanation
     * MyStack myStack = new MyStack();
     * myStack.push(1);
     * myStack.push(2);
     * myStack.top(); // return 2
     * myStack.pop(); // return 2
     * myStack.empty(); // return False
     *
     *
     * Constraints:
     *
     * 1 <= x <= 9
     * At most 100 calls will be made to push, pop, top, and empty.
     * All the calls to pop and top are valid.
     *
     *
     * Follow-up: Can you implement the stack using only one queue?
     *
     *
     *
     */

    class MyStack {

        private Queue<Integer> stack;
        /** Initialize your data structure here. */
        public MyStack() {
            stack = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            Queue<Integer> temp = new LinkedList<>();
            while (!stack.isEmpty()) {
                temp.offer(stack.poll());
            }

            stack.offer(x);
            while (!temp.isEmpty()) {
                stack.offer(temp.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return stack.poll();
        }

        /** Get the top element. */
        public int top() {
            return stack.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }

    /**
     * Decode String
     *
     * Given an encoded string, return its decoded string.
     *
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being
     * repeated exactly k times. Note that k is guaranteed to be a positive integer.
     *
     * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed,
     * etc.
     *
     * Furthermore, you may assume that the original data does not contain any digits and that digits are only for
     * those repeat numbers, k. For example, there won't be input like 3a or 2[4].
     *
     *
     *
     * Example 1:
     *
     * Input: s = "3[a]2[bc]"
     * Output: "aaabcbc"
     * Example 2:
     *
     * Input: s = "3[a2[c]]"
     * Output: "accaccacc"
     * Example 3:
     *
     * Input: s = "2[abc]3[cd]ef"
     * Output: "abcabccdcdcdef"
     * Example 4:
     *
     * Input: s = "abc3[cd]xyz"
     * Output: "abccdcdcdxyz"
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 30
     * s consists of lowercase English letters, digits, and square brackets '[]'.
     * s is guaranteed to be a valid input.
     * All the integers in s are in the range [1, 300].
     *
     *
     */
    public String decodeString(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) queue.offer(c);
        return helper(queue);
    }

    public String helper(Queue<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            char c= queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                String sub = helper(queue);
                for (int i = 0; i < num; i++) sb.append(sub);
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public String decodeStringNonRecursive(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<String> result = new Stack<>();
        int i = 0;
        result.push("");
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int start = i;
                while (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                count.push(Integer.parseInt(s.substring(start, i + 1)));
            } else if (ch == '[') {
                result.push("");
            } else if (ch == ']') {
                String str = result.pop();
                StringBuilder sb = new StringBuilder();
                int times = count.pop();
                for (int j = 0; j < times; j += 1) {
                    sb.append(str);
                }
                result.push(result.pop() + sb.toString());
            } else {
                result.push(result.pop() + ch);
            }
            i += 1;
        }
        return result.pop();
    }

    /**
     * Flood Fill
     *
     * An image is represented by an m x n integer grid image where image[i][j] represents the
     * pixel value of the image.
     *
     * You are also given three integers sr, sc, and newColor. You should perform a
     * flood fill on the image starting from the pixel image[sr][sc].
     *
     * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally
     * to the starting pixel of the same color as the starting pixel, plus any pixels connected
     * 4-directionally to those pixels (also with the same color), and so on.
     * Replace the color of all of the aforementioned pixels with newColor.
     *
     * Return the modified image after performing the flood fill.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
     * Output: [[2,2,2],[2,2,0],[2,0,1]]
     * Explanation: From the center of the image with position (sr, sc) = (1, 1)
     * (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel
     * (i.e., the blue pixels) are colored with the new color.
     * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the
     * starting pixel.
     *
     * Example 2:
     *
     * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
     * Output: [[2,2,2],[2,2,2]]
     *
     *
     * Constraints:
     *
     * m == image.length
     * n == image[i].length
     * 1 <= m, n <= 50
     * 0 <= image[i][j], newColor < 216
     * 0 <= sr < m
     * 0 <= sc < n
     *
     */

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void fill(int[][] image, int sr, int sc, int color, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) return;
        image[sr][sc] = newColor;
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }

    /**
     * 01 Matrix
     *
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
     * Output: [[0,0,0],[0,1,0],[0,0,0]]
     * Example 2:
     *
     *
     * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
     * Output: [[0,0,0],[0,1,0],[1,2,1]]
     *
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 104
     * 1 <= m * n <= 104
     * mat[i][j] is either 0 or 1.
     * There is at least one 0 in mat.
     *
     */
    public int[][] updateMatrix(int[][] mat) {

    }

}
