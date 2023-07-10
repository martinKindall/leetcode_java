package org.walruscode.leetcode.medium;

import org.walruscode.leetcode.utils.ListNode;

public class P445_two_numbers_II {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int length1 = length(l1);
        int length2 = length(l2);

        int len1 = length1;
        int len2 = length2;

        ListNode head1 = l1;
        ListNode head2 = l2;

        while (l1 != null && l2 != null) {
            if (length1 > length2) {
                l1 = l1.next;
                length1--;
            } else if (length2 > length1) {
                l2 = l2.next;
                length2--;
            } else {
                int sum = l1.val + l2.val;

                if (len1 > len2) {
                    l1.val = sum;
                } else {
                    l2.val = sum;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
        }

        ListNode result = len1 > len2 ? head1 : head2;

        ListNode final2 = fixdigits(result);

        if (final2 != null && final2.val >= 10) {
            final2.val -= 10;
            return new ListNode(1, final2);
        }

        return final2;
    }

    public ListNode fixdigits(ListNode list) {
        if (list == null || list.next == null) return list;

        ListNode sub = fixdigits(list.next);
        if (sub != null && sub.val >= 10) {
            sub.val -= 10;
            list.val++;
        }

        return list;
    }

    public int length(ListNode list) {
        int sum = 0;

        while (list != null) {
            sum++;
            list = list.next;
        }

        return sum;
    }

    public ListNode addTwoNumbersV1(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        int carry = 0;
        ListNode result = null;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }

            ListNode tmp = new ListNode(sum, result);
            result = tmp;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode rest = l1 == null ? l2 : l1;
        while (rest != null) {
            int sum = carry + rest.val;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            ListNode tmp = new ListNode(sum, result);
            result = tmp;
            rest = rest.next;
        }

        if (carry == 1) return new ListNode(1, result);

        return result;
    }

    public ListNode reverseList(ListNode list) {
        if (list == null || list.next == null) return list;

        ListNode head = list;
        ListNode prev = null;
        ListNode aux = list;

        while (head != null) {
            head = head.next;
            aux.next = prev;
            prev = aux;
            aux = head;
        }

        return prev;
    }

    public ListNode addTwoNumbersNotWorking(ListNode l1, ListNode l2) {
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        while (l1 != null) {
            num1.append(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            num2.append(l2.val);
            l2 = l2.next;
        }

        long num1_a = Long.parseLong(num1.toString());
        long num2_a = Long.parseLong(num2.toString());

        long result = num1_a + num2_a;
        ListNode head = null;

        if (result == 0) return new ListNode(0);

        while (result != 0) {
            int digit = (int) (result % 10);
            ListNode temp = new ListNode(digit, head);
            head = temp;
            result /= 10;
        }

        return head;
    }
}
