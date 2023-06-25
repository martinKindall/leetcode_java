package org.walruscode.leetcode;

public class P1608_x_grater_than {

    // time O(nlogn)
    public int specialArray(int[] nums) {
        if (nums.length == 1 && nums[0] > 0) return 1;

        int start = 0, end = nums.length;

        while (start <= end) {
            int mid = (start + end) / 2;
            int countG = 0;

            for (int i: nums) {
                if (i >= mid) countG++;
            }

            if (countG > mid) start = mid + 1;

            else if (countG < mid) end = mid - 1;

            else return mid;
        }

        return -1;
    }

    // time O(n^2)
    public int specialArrayOld(int[] nums) {

        int count = 0;

        for (int i = 0; i <= nums.length; i++) {
            count = 0;

            for (int j = 0; j < nums.length; j++) {
                if (nums[j] >= i) count++;
                if (count > i) break;
            }

            if (count == i) return i;
        }

        return -1;
    }
}
