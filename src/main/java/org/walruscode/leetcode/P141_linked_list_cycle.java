package org.walruscode.leetcode;

import org.walruscode.leetcode.utils.ListNode;

public class P141_linked_list_cycle {

    public boolean hasCycle(ListNode head) {

        ListNode slowPointer = head;
        while (head != null && slowPointer != null) {
            slowPointer = slowPointer.next;

            if (head.next != null) {
                head = head.next.next;

                if (head == slowPointer) return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
