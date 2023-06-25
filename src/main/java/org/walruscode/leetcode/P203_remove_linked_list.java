package org.walruscode.leetcode;

public class P203_remove_linked_list {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode partial = removeElements(head.next, val);

        if (head.val == val) return partial;

        head.next = partial;
        return head;
    }

    public ListNode removeElementsRecursiveOld(ListNode head, int val) {
        if (head == null) return null;

        if (head.val == val) return removeElementsRecursiveOld(head.next, val);

        return new ListNode(head.val, removeElementsRecursiveOld(head.next, val));
    }

    public ListNode removeElementsV2(ListNode head, int val) {
        if (head == null) return null;

        ListNode init = new ListNode(0, head);
        ListNode prev = init;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }

            curr = curr.next;
        }

        return init.next;
    }
    
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
