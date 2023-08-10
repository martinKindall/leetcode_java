package org.walruscode.leetcode.medium;

public class P55_jump_game {

    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;

        int end = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (farthest >= nums.length - 1) return true;

            if (i == end) {
                if (end == farthest) return false;
                end = farthest;
            }
        }

        return true;
    }
}
