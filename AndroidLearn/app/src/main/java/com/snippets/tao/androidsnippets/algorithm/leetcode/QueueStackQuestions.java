package com.snippets.tao.androidsnippets.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

}
