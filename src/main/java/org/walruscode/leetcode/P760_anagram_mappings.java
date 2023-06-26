package org.walruscode.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P760_anagram_mappings {

    public int[] anagramMappings(int[] nums1, int[] nums2) {
        if (nums1.length == 1) return new int[]{0};

        int[] result = new int[nums1.length];

        Map<Integer, Integer> myMap = new HashMap<>();

        for (int j = 0; j < nums2.length; j++) {
            myMap.put(nums2[j], j);
        }

        for (int i = 0; i < nums1.length; i++) {
            int current = nums1[i];

            result[i] = myMap.get(current);
        }

        return result;
    }

    public int[] anagramMappingsV2(int[] nums1, int[] nums2) {
        if (nums1.length == 1) return new int[]{0};

        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int current = nums1[i];

            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == current) result[i] = j;
            }
        }

        return result;
    }
}
