package org.walruscode.leetcode;

public class P706_hashmap {

    class ListNode {
        int key, val;
        ListNode next;

        public ListNode(int key, int val, ListNode next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    class MyHashMap {
        static final int size = 19997;
        static final int mult = 12582917;
        ListNode[] data;

        public MyHashMap() {
            this.data = new ListNode[size];
        }

        private int hash(int key) {
            return (int) ((long) key * mult % size);
        }

        public void put(int key, int value) {
            remove(key);
            int h = hash(key);
            ListNode node = new ListNode(key, value, data[h]);
            data[h] = node;
        }

        public int get(int key) {
            int h = hash(key);
            if (data[h] == null) return -1;

            ListNode list = data[h];
            while (list != null) {
                if (list.key == key) return list.val;
                list = list.next;
            }

            return -1;
        }

        public void remove(int key) {
            int h = hash(key);
            ListNode list = data[h];
            ListNode head = new ListNode(0, 0, list);
            ListNode prev = head;

            while (list != null) {
                if (list.key == key) {
                    prev.next = list.next;
                    break;
                } else {
                    prev = list;
                    list = list.next;
                }
            }
            data[h] = head.next;
        }
    }

    class MyHashMapOld {

        int[] myMap;
        int[] keys;

        public MyHashMapOld() {
            myMap = new int[1000001];
            keys = new int[1000001];
        }

        public void put(int key, int value) {
            myMap[key] = value;
            keys[key] = 1;
        }

        public int get(int key) {
            if (keys[key] != 1) return -1;

            return myMap[key];
        }

        public void remove(int key) {
            keys[key] = 0;
        }
    }
}
