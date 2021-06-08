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

    /**
     * LeetCode 206. Reverse Linked List
     *
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,2,3,4,5]
     * Output: [5,4,3,2,1]
     * Example 2:
     *
     *
     * Input: head = [1,2]
     * Output: [2,1]
     * Example 3:
     *
     * Input: head = []
     * Output: []
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is the range [0, 5000].
     * -5000 <= Node.val <= 5000
     *
     *
     * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
     *
     *
     */

    // Iterative version;
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    // Recursive Version
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     *
     * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
     * and return the new head.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,2,6,3,4,5,6], val = 6
     * Output: [1,2,3,4,5]
     * Example 2:
     *
     * Input: head = [], val = 1
     * Output: []
     * Example 3:
     *
     * Input: head = [7,7,7,7], val = 7
     * Output: []
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [0, 104].
     * 1 <= Node.val <= 50
     * 0 <= k <= 50
     *
     *
     *
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     *
     * Given the head of a singly linked list, group all the nodes with odd indices together followed by
     * the nodes with even indices, and return the reordered list.
     *
     * The first node is considered odd, and the second node is even, and so on.
     *
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,2,3,4,5]
     * Output: [1,3,5,2,4]
     * Example 2:
     *
     *
     * Input: head = [2,1,3,5,6,4,7]
     * Output: [2,3,6,7,1,5,4]
     *
     *
     * Constraints:
     *
     * The number of nodes in the linked list is in the range [0, 104].
     * -106 <= Node.val <= 106
     *
     *
     * Follow up: Could you solve it in O(1) space complexity and O(nodes) time complexity?
     *
     *
     *
     */

    public ListNode oddEvenList(ListNode head) {

        if (head != null) {

            ListNode odd = head, even = head.next, evenHead = even;

            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }


    /**
     * Given the head of a singly linked list, return true if it is a palindrome.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,2,2,1]
     * Output: true
     * Example 2:
     *
     *
     * Input: head = [1,2]
     * Output: false
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [1, 105].
     * 0 <= Node.val <= 9
     *
     *
     * Follow up: Could you do it in O(n) time and O(1) space?
     *
     */

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }


    /**
     * Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together
     * the nodes of the first two lists.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: l1 = [1,2,4], l2 = [1,3,4]
     * Output: [1,1,2,3,4,4]
     * Example 2:
     *
     * Input: l1 = [], l2 = []
     * Output: []
     * Example 3:
     *
     * Input: l1 = [], l2 = [0]
     * Output: [0]
     *
     *
     * Constraints:
     *
     * The number of nodes in both lists is in the range [0, 50].
     * -100 <= Node.val <= 100
     * Both l1 and l2 are sorted in non-decreasing order.
     *
     *
     */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


    /**
     *
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     * Example 2:
     *
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     * Example 3:
     *
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     *
     *
     * Constraints:
     *
     * The number of nodes in each linked list is in the range [1, 100].
     * 0 <= Node.val <= 9
     * It is guaranteed that the list represents a number that does not have leading zeros.
     *
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        boolean incre = false;
        ListNode newHead = null;
        ListNode cur = null;
        while(l1 != null && l2 != null) {
            int sum = l1.val + l2.val + (incre ? 1 : 0);
            if (sum > 9) {
                incre = true;
            } else {
                incre = false;
            }

            ListNode node = new ListNode(sum % 10);
            if (newHead == null) {
                newHead = node;
                cur = node;
            } else {
                cur.next = node;
                cur = cur.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + (incre ? 1 : 0);
            if (sum > 9) {
                incre = true;
            } else {
                incre = false;
            }

            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = cur.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + (incre ? 1 : 0);
            if (sum > 9) {
                incre = true;
            } else {
                incre = false;
            }

            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = cur.next;
            l2 = l2.next;
        }

        if (incre) {
            ListNode node = new ListNode(1);
            cur.next = node;
            cur = cur.next;
        }
        return newHead;
    }

    /**
     *
     * You are given a doubly linked list which in addition to the next and previous pointers,
     * it could have a child pointer, which may or may not point to a separate doubly linked list.
     * These child lists may have one or more children of their own, and so on, to produce a multilevel data structure,
     * as shown in the example below.
     *
     * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
     * You are given the head of the first level of the list.
     *
     *
     *
     * Example 1:
     *
     * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
     * Explanation:
     *
     * The multilevel linked list in the input is as follows:
     *
     *
     *
     * After flattening the multilevel linked list it becomes:
     *
     *
     * Example 2:
     *
     * Input: head = [1,2,null,3]
     * Output: [1,3,2]
     * Explanation:
     *
     * The input multilevel linked list is as follows:
     *
     *   1---2---NULL
     *   |
     *   3---NULL
     * Example 3:
     *
     * Input: head = []
     * Output: []
     *
     *
     * How multilevel linked list is represented in test case:
     *
     * We use the multilevel linked list from Example 1 above:
     *
     *  1---2---3---4---5---6--NULL
     *          |
     *          7---8---9---10--NULL
     *              |
     *              11--12--NULL
     * The serialization of each level is as follows:
     *
     * [1,2,3,4,5,6,null]
     * [7,8,9,10,null]
     * [11,12,null]
     * To serialize all levels together we will add nulls in each level to signify no node connects to the upper
     * node of the previous level. The serialization becomes:
     *
     * [1,2,3,4,5,6,null]
     * [null,null,7,8,9,10,null]
     * [null,11,12,null]
     * Merging the serialization of each level and removing trailing nulls we obtain:
     *
     * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     *
     *
     * Constraints:
     *
     * The number of Nodes will not exceed 1000.
     * 1 <= Node.val <= 105
     *
     *
     */
    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    public Node flatten(Node head) {
        if(head == null) {
            return head;
        }

        Node cur = head;
        while(cur != null) {
            if(cur.child == null) {
                cur = cur.next;
                continue;
            }

            Node temp = cur.child;
            while(temp.next != null) {
                temp = temp.next;
            }

            temp.next = cur.next;
            if(cur.next != null) {
                cur.next.prev = temp;
            }
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null;
        }
        return head;
    }
}
