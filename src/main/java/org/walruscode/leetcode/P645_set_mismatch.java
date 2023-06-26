package org.walruscode.leetcode;

import java.util.HashSet;
import java.util.Set;

public class P645_set_mismatch {

    public int[] findErrorNums(int[] nums) {
        int repeated = -1;

        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;

            if (nums[idx] < 0) {
                repeated = idx + 1;
            } else {
                nums[idx] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return new int[]{repeated, i + 1};
            }
        }

        throw new IllegalArgumentException("This should have not happened");
    }

    public int[] findErrorNumsOld(int[] nums) {

        int lastNum = -1;
        int repeated = -1;
        int totalSum = 0;

        Set<Integer> mySet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            if (mySet.contains(nums[i])) repeated = nums[i];
            mySet.add(nums[i]);
        }

        totalSum -= repeated;

        int realSum = nums.length*(nums.length + 1) / 2;

        return new int[]{repeated, realSum - totalSum};
    }
}
