package org.walruscode.leetcode.medium;

import org.walruscode.leetcode.utils.TreeNode;

public class P230_smallest_k_elem_bst {

    private static class Pair {
        final int idx;
        final int val;

        Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }


    public int kthSmallest(TreeNode root, int k) {

        Pair result = kthSmallestAux(root, k, new Pair(1, -1));

        return result.val;
    }

    private Pair kthSmallestAux(TreeNode root, int k, Pair result) {
        if (root == null) return result;

        Pair left = kthSmallestAux(root.left, k, result);

        if (left.val != -1) return left;

        if (left.idx == k) {
            return new Pair(k, root.val);
        }

        return kthSmallestAux(root.right, k, new Pair(left.idx + 1, -1));
    }

    private int result;
    private int k;
    private boolean stop;

    public int kthSmallestStateful(TreeNode root, int k) {

        result = -1;
        this.k = k;
        stop = false;

        recursive(root);

        return result;
    }

    private void recursive(TreeNode root) {
        if (root == null) return;

        recursive(root.left);

        if (stop) return;

        if (k == 1) {
            result = root.val;
            stop = true;
            return;
        }

        k--;

        recursive(root.right);
    }
}
