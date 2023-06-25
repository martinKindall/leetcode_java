package org.walruscode.leetcode;

public class P1437_length_k_places {

    public boolean kLengthApart(int[] nums, int k) {
        if (nums.length == 1) return true;

        int prev = -(k+1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1 && i - prev - 1 < k) return false;
            if (nums[i] == 1) prev = i;
        }

        return true;
    }
}
