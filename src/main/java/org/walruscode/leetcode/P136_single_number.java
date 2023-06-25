package org.walruscode.leetcode;

public class P136_single_number {

    public int singleNumber(int[] nums) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum = sum ^ nums[i];
        }

        return sum;
    }
}
