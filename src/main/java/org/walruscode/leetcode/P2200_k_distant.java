package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P2200_k_distant {

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        var coincidences = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) coincidences.add(i);
        }

        int lastIdx = -1;

        var result = new ArrayList<Integer>();
        for (Integer coincidence: coincidences) {
            for (int j = Math.max(0, Math.max(lastIdx, coincidence-k)); j <= Math.min(coincidence + k, nums.length-1); j++) {
                result.add(j);
                lastIdx = j+1;
            }
        }

        return result;
    }
}
