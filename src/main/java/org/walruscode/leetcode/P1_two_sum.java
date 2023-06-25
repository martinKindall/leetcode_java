package org.walruscode.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P1_two_sum {

    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 2) return new int[]{0,1};

        Map<Integer, Integer> numbers = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            numbers.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int rest = target - nums[i];

            if (numbers.containsKey(rest) && numbers.get(rest) != i) {
                return new int[]{i, numbers.get(rest)};
            }
        }

        throw new RuntimeException("This shouldn't have happened. Invalid nums/target.");
    }
}
