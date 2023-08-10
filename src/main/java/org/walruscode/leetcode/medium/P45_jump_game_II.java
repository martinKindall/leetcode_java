package org.walruscode.leetcode.medium;

public class P45_jump_game_II {

    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums.length == 2) return 1;

        int ans = 0;
        int end = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest >= nums.length - 1) {
                ++ans;
                break;
            }

            if (i == end) {
                ++ans;
                end = farthest;
            }
        }

        return ans;
    }

    public int jumpV2(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums.length == 2) return 1;

        int jumps = 0;

        int i = 0;

        while (i < nums.length - 1) {
            int current = nums[i];

            if (i + current >= nums.length - 1) return jumps + 1;

            int max = 0;
            int valMax = 0;

            for (int j = i+1; (j <= i + current) && (j < nums.length); j++) {
                if (j+nums[j] >= valMax) {
                    max = j;
                    valMax = j + nums[j];
                }
            }

            jumps++;
            i = max;
        }

        return jumps;
    }
}
