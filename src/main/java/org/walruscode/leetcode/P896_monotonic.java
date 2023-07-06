package org.walruscode.leetcode;

public class P896_monotonic {

    public boolean isMonotonic(int[] nums) {
        if (nums.length <= 2) return true;

        boolean set = false;
        boolean direction = false;

        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i-1];

            if (set && diff != 0) {
                if (diff > 0 != direction) return false;
            } else if (diff != 0) {
                direction = diff > 0;
                set = true;
            }
        }

        return true;
    }
}
