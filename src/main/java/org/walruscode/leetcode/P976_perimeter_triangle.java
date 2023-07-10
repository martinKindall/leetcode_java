package org.walruscode.leetcode;

import java.util.Arrays;

public class P976_perimeter_triangle {

    public int largestPerimeter(int[] nums) {
        // non-zero means a + b > c for every combination of triangle sides

        Arrays.sort(nums);

        for(int i = nums.length - 1; i > 1; i--) {
            if (nums[i-1] + nums[i-2] > nums[i]) return nums[i-1] + nums[i-2] + nums[i];
        }

        return 0;
    }
}
