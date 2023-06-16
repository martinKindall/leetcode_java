package org.walruscode.leetcode;

public class P234_palindrome_list {

    // Using Floyd's reversal from https://leetcode.com/sgallivan
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        if (head.next == null) return true;

        ListNode headPoint = head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;

            if (fast == null) {
                break;
            }
            fast = fast.next;
        }

        ListNode aux = slow;
        ListNode prev = null;

        while (true) {
            slow = slow.next;
            aux.next = prev;
            prev = aux;
            if (slow == null) {
                break;
            }
            aux = slow;
        }

        while (aux != null) {
            if (aux.val != headPoint.val) return false;
            aux = aux.next;
            headPoint = headPoint.next;
        }

        return true;
    }

    public boolean isPalindromeLinearSpace(ListNode head) {
        if (head == null) return false;
        if (head.next == null) return true;

        ListNode headPoint = head;
        ListNode newHead = null;

        // create reverse
        while (head != null) {
            newHead = new ListNode(head.val, newHead);

            head = head.next;
        }

        // compare
        while (headPoint != null) {
            if (headPoint.val != newHead.val) return false;
            headPoint = headPoint.next;
            newHead = newHead.next;
        }

        return true;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
