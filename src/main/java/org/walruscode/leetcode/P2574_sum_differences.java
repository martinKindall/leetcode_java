package org.walruscode.leetcode;

public class P2574_sum_differences {

    public int[] leftRightDifference(int[] nums) {
        if (nums.length == 1) return new int[1];

        int[] answer = new int[nums.length];

        int leftSum = 0;
        int total = 0;

        for (int i: nums) total += i;

        for (int j = 0; j < nums.length; j++) {
            if (j > 0) {
                leftSum += nums[j-1];
            }
            answer[j] = Math.abs((leftSum << 1) - total + nums[j]);
        }

        return answer;
    }
}
