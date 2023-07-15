package org.walruscode.leetcode.medium;

public class P707_linked_list {

    class MyLinkedList {

        private static class Node {
            int val;
            Node next;

            Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }

            Node() {}
        }

        Node head;
        int size;

        public MyLinkedList() {
            head = new Node();
            this.size = 0;
        }

        public int get(int index) {
            if (index < 0 || (index + 1 > size)) return -1;

            int i = 0;
            Node current = head.next;

            while (current != null) {
                if (i < index) {
                    i++;
                    current = current.next;
                    continue;
                }

                return current.val;
            }

            return -1;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index > size || index < 0) return;

            Node current = head.next;
            Node prev = head;

            int i = 0;
            while (i++ < index) {
                prev = current;
                current = current.next;
            }

            prev.next = new Node(val, current);

            size++;
        }

        public void deleteAtIndex(int index) {
            if (index >= size || index < 0) return;

            Node current = head.next;
            Node prev = head;

            int i = 0;
            while (i++ < index) {
                prev = current;
                current = current.next;
            }

            prev.next = current.next;

            size--;
        }
    }
}
