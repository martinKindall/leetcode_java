package org.walruscode.leetcode;

import org.walruscode.leetcode.utils.TreeNode;

public class P112_path_sum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        return hasPathSumAux(root, targetSum, 0);
    }

    private boolean hasPathSumAux(TreeNode root, int targetSum, int accum) {
        if (root.left == null && root.right == null) {
            return accum + root.val == targetSum;
        }

        if (root.left != null && root.right != null) {
            return hasPathSumAux(root.left, targetSum, accum + root.val) || hasPathSumAux(root.right, targetSum, accum + root.val);
        }

        if (root.left != null) return hasPathSumAux(root.left, targetSum, accum + root.val);

        return hasPathSumAux(root.right, targetSum, accum + root.val);
    }
}
