package org.walruscode.leetcode;

public class P35_search_insert {

    public static void main(String[] args) {
        new P35_search_insert().searchInsert(new int[]{1, 3, 5, 6}, 2);
    }

    public int searchInsert(int[] nums, int target) {
        // this screams for binary search

        if (nums.length == 1) {
            if (target == nums[0] || target < nums[0]) return 0;
            return 1;
        }

        int middle = nums.length / 2;
        int init = 0;
        int end = nums.length - 1;

        while (init <= end) {
            if (nums[middle] == target) return middle;

            if (init >= end) break;

            if (nums[middle] > target) {
                end = middle - 1;
                middle = (init + end) / 2;
            } else {
                init = middle + 1;
                middle = (init + end) / 2;
            }
        }

        if (nums[middle] < target) return middle + 1;
        if (nums[middle] > target) return middle;

        return middle;
    }
}
