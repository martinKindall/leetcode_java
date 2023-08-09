package org.walruscode.leetcode.medium;

import org.walruscode.leetcode.utils.ListNode;

public class P19_remove_nth_node_end_list {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) return null;

        ListNode starter = new ListNode(0, head);
        ListNode copy = starter;

        int counter = n + 2;

        ListNode slow = copy;

        while (copy != null) {
            if (--counter <= 0) {
                slow = slow.next;
            }

            copy = copy.next;
        }

        slow.next = slow.next.next;

        return starter.next;
    }

    public ListNode removeNthFromEndV2(ListNode head, int n) {
        if (head.next == null) return null;

        ListNode starter = new ListNode(0, head);
        ListNode copy = starter;

        ListNode[] nodes = new ListNode[31];

        int i = 0;

        while (copy != null) {
            nodes[i++] = copy;
            copy = copy.next;
        }

        ListNode previous = nodes[i - n - 1];

        previous.next = previous.next.next;

        return starter.next;
    }
}
