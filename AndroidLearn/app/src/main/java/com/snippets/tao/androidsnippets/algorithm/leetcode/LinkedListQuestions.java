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

        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode noden4 = new ListNode(-4);
        node3.next = node2;
        node2.next = node0;
        node0.next = noden4;
        noden4.next = node2;

        ListNode begin = detectCycle(node3);


        System.out.println("hasCycle: " + begin.val);
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
     *
     *
     *
     *  Your MyLinkedList object will be instantiated and called as such:
     *  MyLinkedList obj = new MyLinkedList();
     *  int param_1 = obj.get(index);
     *  obj.addAtHead(val);
     *  obj.addAtTail(val);
     *  obj.addAtIndex(index,val);
     *  obj.deleteAtIndex(index);
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
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     *
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
     * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
     * connected to. Note that pos is not passed as a parameter.
     *
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [3,2,0,-4], pos = 1
     * Output: true
     * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
     * Example 2:
     *
     *
     * Input: head = [1,2], pos = 0
     * Output: true
     * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
     * Example 3:
     *
     *
     * Input: head = [1], pos = -1
     * Output: false
     * Explanation: There is no cycle in the linked list.
     *
     *
     * Constraints:
     *
     * The number of the nodes in the list is in the range [0, 104].
     * -105 <= Node.val <= 105
     * pos is -1 or a valid index in the linked-list.
     *
     *
     * Follow up: Can you solve it using O(1) (i.e. constant) memory?
     *
     */

      //Definition for singly-linked list.
     private static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
      }


    public static boolean hasCycle(ListNode head) {
         if (head == null)
             return false;

         ListNode fast = head;
         ListNode slow = head;

         while (fast.next != null && fast.next.next != null) {
             fast = fast.next.next;
             slow = slow.next;
             if (fast == slow)
                 return true;
         }

         return false;
    }

    /**
     *
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
     * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
     * is connected to. Note that pos is not passed as a parameter.
     *
     * Notice that you should not modify the linked list.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [3,2,0,-4], pos = 1
     * Output: tail connects to node index 1
     * Explanation: There is a cycle in the linked list, where tail connects to the second node.
     * Example 2:
     *
     *
     * Input: head = [1,2], pos = 0
     * Output: tail connects to node index 0
     * Explanation: There is a cycle in the linked list, where tail connects to the first node.
     * Example 3:
     *
     *
     * Input: head = [1], pos = -1
     * Output: no cycle
     * Explanation: There is no cycle in the linked list.
     *
     *
     * Constraints:
     *
     * The number of the nodes in the list is in the range [0, 104].
     * -105 <= Node.val <= 105
     * pos is -1 or a valid index in the linked-list.
     *
     *
     * Follow up: Can you solve it using O(1) (i.e. constant) memory?
     *
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode search = head;
                while(search != slow) {
                    search = search.next;
                    slow = slow.next;
                }

                return slow;
            }
        }

        return null;
    }


    /**
     *
     * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
     * If the two linked lists have no intersection at all, return null.
     *
     * For example, the following two linked lists begin to intersect at node c1:
     *
     *
     * It is guaranteed that there are no cycles anywhere in the entire linked structure.
     *
     * Note that the linked lists must retain their original structure after the function returns.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
     * Output: Intersected at '8'
     * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
     * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2
     * nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
     * Example 2:
     *
     *
     * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * Output: Intersected at '2'
     * Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
     * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes
     * before the intersected node in A; There are 1 node before the intersected node in B.
     * Example 3:
     *
     *
     * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * Output: No intersection
     * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the
     * two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
     * Explanation: The two lists do not intersect, so return null.
     *
     *
     * Constraints:
     *
     * The number of nodes of listA is in the m.
     * The number of nodes of listB is in the n.
     * 0 <= m, n <= 3 * 104
     * 1 <= Node.val <= 105
     * 0 <= skipA <= m
     * 0 <= skipB <= n
     * intersectVal is 0 if listA and listB do not intersect.
     * intersectVal == listA[skipA + 1] == listB[skipB + 1] if listA and listB intersect.
     *
     *
     * Follow up: Could you write a solution that runs in O(n) time and use only O(1) memory?
     *
     *
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode curA = headA, curB = headB;
        while(curA != curB){
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }

}
