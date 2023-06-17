package org.walruscode.leetcode;

public class P1480_running_sum {

    public int[] runningSum(int[] nums) {

        int accum = 0;

        for (int i = 0; i < nums.length; i++) {
            nums[i] = accum + nums[i];
            accum = nums[i];
        }

        return nums;
    }
}
