package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P705_hashset {

    static class MyHashSet {

        private final boolean[] array;

        public MyHashSet() {
            array = new boolean[1000000+1];
        }

        public void add(int key) {
            array[key] = true;
        }

        public void remove(int key) {
            array[key] = false;
        }

        public boolean contains(int key) {
            return array[key];
        }
    }

    static class MyHashSetV2 {

        public static final int N_BUCKETS = 10;
        private final List<List<Integer>> list;

        public MyHashSetV2() {
            list = new ArrayList<>();

            for (int i = 0; i < N_BUCKETS; i++) {
                list.add(new ArrayList<>());
            }
        }

        public void add(int key) {
            List<Integer> current = list.get(key % N_BUCKETS);

            if (!current.contains(key)) {
                current.add(key);
            }
        }

        public void remove(int key) {
            List<Integer> current = list.get(key % N_BUCKETS);
            current.remove(Integer.valueOf(key));
        }

        public boolean contains(int key) {
            List<Integer> current = list.get(key % N_BUCKETS);
            return current.contains(key);
        }
    }
}
