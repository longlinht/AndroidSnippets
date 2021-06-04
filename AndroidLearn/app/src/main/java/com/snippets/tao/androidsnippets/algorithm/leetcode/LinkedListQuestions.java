package com.snippets.tao.androidsnippets.algorithm.leetcode;

/**
 * Created by Tao He on 2021/6/3.
 * hetaoof@gmail.com
 */
public class LinkedListQuestions {

    public static void test() {

        /*
        ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail",
        "get","addAtHead","addAtIndex","addAtHead"]
[[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]

         */

        /**
         *
         * ["MyLinkedList","addAtHead","get","addAtHead","addAtHead","deleteAtIndex","addAtHead",
         * "get","get","get","addAtHead","deleteAtIndex"]
         * [[],[4],[1],[1],[5],[3],[7],[3],[3],[3],[1],[4]]
         *
         */

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(4);

        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3, 0);

        myLinkedList.deleteAtIndex(2);
        System.out.println("----- ");
        myLinkedList.printSelf();

        myLinkedList.addAtHead(6);
        myLinkedList.addAtTail(4);
        System.out.println("before get----- ");
        myLinkedList.printSelf();

        int value = myLinkedList.get(4);
        System.out.println("value： " + value);
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5, 0);
        myLinkedList.addAtHead(6);

        myLinkedList.addAtTail(1);
        myLinkedList.get(0);

        myLinkedList.printSelf();
        // myLinkedList.get(1);              // return 2
        // myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        // myLinkedList.get(1);

    }

    /**
     * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
     * A node in a singly linked list should have two attributes: val and next. val is the value of the current node,
     * and next is a pointer/reference to the next node.
     * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node
     * in the linked list. Assume all nodes in the linked list are 0-indexed.
     *
     * Implement the MyLinkedList class:
     *
     * MyLinkedList() Initializes the MyLinkedList object.
     * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
     * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     * void addAtTail(int val) Append a node of value val as the last element of the linked list.
     * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
     * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
     *
     *
     * Example 1:
     *
     * Input
     * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
     * [[], [1], [3], [1, 2], [1], [1], [1]]
     * Output
     * [null, null, null, null, 2, null, 3]
     *
     * Explanation
     * MyLinkedList myLinkedList = new MyLinkedList();
     * myLinkedList.addAtHead(1);
     * myLinkedList.addAtTail(3);
     * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
     * myLinkedList.get(1);              // return 2
     * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
     * myLinkedList.get(1);              // return 3
     *
     *
     * Constraints:
     *
     * 0 <= index, val <= 1000
     * Please do not use the built-in LinkedList library.
     * At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
     *
     *
     *
     * ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get",
     * "addAtHead","addAtIndex","addAtHead"]
     * [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
     *
     */

    private static class MyLinkedList {

        // Definition for singly-linked list.
        public class Node {
            int val;
            Node next;
            Node(int x) { val = x; }
        }

        private int size;
        private Node head;

        /** Initialize your data structure here. */
        public MyLinkedList() {
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if(index >= size) return -1;
            Node curr = head;
            for(int i = 0; i < index; i++) curr = curr.next;
            return curr.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node prev = head;
            head = new Node(val);
            head.next = prev;
            size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            if(this.size == 0) {
                addAtHead(val);
                return;
            }
            Node curr = head;
            while(curr.next != null) curr = curr.next;
            curr.next = new Node(val);
            size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if(index > size) return;
            if(index == 0) {
                addAtHead(val);
            } else if (index == size) {
                addAtTail(val);
            } else {
                Node newNode = new Node(val), curr = head;
                for(int i = 0; i < index - 1; i++) curr = curr.next;
                newNode.next = curr.next;
                curr.next = newNode;
                size++;
            }
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if(index >= size) return;
            if(index == 0) {
                head = head.next;
            } else {
                Node curr = head;
                for(int i = 0; i < index - 1; i++) curr = curr.next;
                curr.next = curr.next.next;
            }
            size--;
        }

        public void printSelf() {
            if (head == null) {
                System.out.println("linked list is empty");
                return;
            }
            Node cur = head;
            if (head.next == null) {
                System.out.println(cur.val);
            }

            while (cur.next != null) {
                System.out.println(cur.val);
                cur = cur.next;

                if(cur.next == null) {
                    System.out.println(cur.val);
                }
            }
        }
    }


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */



}
