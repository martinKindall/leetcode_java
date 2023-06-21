package org.walruscode.leetcode;

public class P206_reverse_list {

    private ListNode aux = null;

    public ListNode reverseList(ListNode head) {
        if (head == null) return aux;

        aux = new ListNode(head.val, aux);

        return reverseList(head.next);
    }

    public ListNode reverseListIterative(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode prev = null;
        ListNode aux = head;

        while (head != null) {
            head = head.next;
            aux.next = prev;
            prev = aux;
            aux = head;
        }

        return prev;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
