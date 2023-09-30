package org.walruscode.leetcode.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P347_top_k_frequent {

    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) return nums;

        Map<Integer, Integer> counts = new HashMap<>();

        for (int num: nums) counts.put(num, counts.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> pQueue = new PriorityQueue<>(k+1, (n1, n2) -> counts.get(n1) - counts.get(n2));

        for (int num: counts.keySet()) {
            pQueue.add(num);

            if (pQueue.size() > k) {
                pQueue.remove();
            }
        }

        return pQueue.stream().mapToInt(i->i).toArray();
    }
}
