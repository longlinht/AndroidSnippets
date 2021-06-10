package com.snippets.tao.androidsnippets.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

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
}
