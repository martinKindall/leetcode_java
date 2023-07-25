package org.walruscode.leetcode;

import org.walruscode.leetcode.utils.TreeNode;

public class P108_array_to_bst {

    // the nums array is already sorted in ascending order
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 1) return new TreeNode(nums[0]);

        TreeNode node = buildTree(nums, 0, nums.length - 1);

        return node;
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;

        TreeNode node = new TreeNode(nums[mid]);

        if (start == end) return node;

        node.left = buildTree(nums, start, mid - 1);
        node.right = buildTree(nums, mid + 1, end);

        return node;
    }
}
