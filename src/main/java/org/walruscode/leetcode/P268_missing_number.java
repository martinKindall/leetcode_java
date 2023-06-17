package org.walruscode.leetcode;

public class P268_missing_number {
    public int missingNumber(int[] nums) {

        int n = nums.length;
        int totalSum = n * (n + 1) / 2;

        for (int i: nums) {
            totalSum -= i;
        }

        return totalSum;
    }
}
