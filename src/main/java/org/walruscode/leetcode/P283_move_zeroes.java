package org.walruscode.leetcode;

public class P283_move_zeroes {

    public void moveZeroes(int[] nums) {

        if (nums.length == 1) return;

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    public void moveZeroesOld(int[] nums) {

        if (nums.length == 1) return;

        int current = 0;

        while (current < nums.length - 1) {
            if (nums[current] == 0) {
                for (int i = current + 1; i < nums.length; i++) {
                    if (nums[i] != 0) {
                        int temp = nums[i];
                        nums[i] = 0;
                        nums[current] = temp;
                        break;
                    }
                }
            }
            current++;
        }
    }
}
