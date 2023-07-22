package org.walruscode.leetcode.medium;

public class P33_rotated_array {

    public int search(int[] nums, int target) {
        if (nums.length == 1 && nums[0] == target) return 0;
        if (nums.length == 1 && nums[0] != target) return -1;

        int start = 0;
        int end = nums.length - 1;
        int mid = (end + start) / 2;

        while (start <= end) {
            int current = nums[mid];

            if (current == target) return mid;

            if (start == end) return -1;

            if (target > current && target > nums[end]) {
                if (current > nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            else if (target > current && target <= nums[end]) start = mid + 1;
            else if (target < current && target < nums[start]) {
                if (current < nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            else if (target < current && target >= nums[start]) end = mid - 1;

            mid = (end + start) / 2;
        }

        return -1;
    }
}
