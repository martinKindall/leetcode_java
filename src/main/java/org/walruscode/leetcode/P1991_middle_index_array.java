package org.walruscode.leetcode;

public class P1991_middle_index_array {

    public int findMiddleIndex(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return 0;

        int rightSum = 0;

        for (int i = 0; i < nums.length; i++) {
            rightSum += nums[i];
        }

        int prevNum = 0;
        int leftSum = 0;

        for (int middleIndex = 0; middleIndex < nums.length; ++middleIndex) {
            leftSum += prevNum;
            rightSum -= nums[middleIndex];

            if (leftSum == rightSum) return middleIndex;

            prevNum = nums[middleIndex];
        }

        return -1;
    }
}
