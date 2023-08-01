package org.walruscode.leetcode;

import org.walruscode.leetcode.utils.ListNode;

import java.util.HashSet;
import java.util.Set;

public class P160_intersection_two_linked_lists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode copyA = headA;
        ListNode copyB = headB;

        int sizeA = 0;
        int sizeB = 0;

        while (copyA != null) {
            copyA = copyA.next;
            ++sizeA;
        }

        while (copyB != null) {
            copyB = copyB.next;
            ++sizeB;
        }

        while (sizeA != sizeB) {
            if (sizeA > sizeB) {
                headA = headA.next;
                --sizeA;
            } else if (sizeA < sizeB) {
                headB = headB.next;
                --sizeB;
            }
        }

        while (headA != null) {
            if (headA == headB) return headA;

            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    public ListNode getIntersectionNodeLinearSpace(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        Set<ListNode> aNodes = new HashSet<>();

        while (headA != null) {
            aNodes.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (aNodes.contains(headB)) return headB;

            headB = headB.next;
        }

        return null;
    }
}
