package org.walruscode.leetcode.medium;

public class P34_first_last_array {
    private int[] result;

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1,-1};
        if (nums.length == 1 && nums[0] != target) return new int[]{-1,-1};
        if (nums.length == 1 && nums[0] == target) return new int[]{0,0};

        result = new int[2];
        result[0] = Integer.MAX_VALUE;
        result[1] = Integer.MIN_VALUE;

        int start = 0;
        int end = nums.length - 1;

        searchRevursive(nums, target, start, end);

        if (result[0] == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        return result;
    }

    private void searchRevursive(int[] nums, int target, int start, int end) {
        if (start <= end && target <= nums[end] && target >= nums[start])  {
            int mid = (start + end) / 2;
            int current = nums[mid];

            if (current == target) {
                result[0] = Math.min(result[0], mid);
                result[1] = Math.max(result[1], mid);

                searchRevursive(nums, target, start, mid-1);
                searchRevursive(nums, target, mid+1, end);
            } else if (current < target) {
                searchRevursive(nums, target, mid+1, end);
            } else {
                searchRevursive(nums, target, start, mid-1);
            }
        }
    }
}
