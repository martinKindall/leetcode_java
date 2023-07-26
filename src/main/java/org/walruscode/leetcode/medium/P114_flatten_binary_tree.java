package org.walruscode.leetcode.medium;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class P114_flatten_binary_tree {

    public void flatten(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return;

        if (root.left != null) {
            TreeNode rightMost = right(root.left);
            TreeNode oldRight = root.right;
            root.right = root.left;
            root.left = null;
            rightMost.right = oldRight;
        }

        flatten(root.right);
    }

    private TreeNode right(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    private List<TreeNode> result;

    public void flattenV2(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return;

        result = new LinkedList<>();

        copyToList(root);

        boolean first = true;
        for (TreeNode current: result) {
            if (first) {
                first = false;
                continue;
            }

            root.left = null;
            root.right = current;
            root = current;
        }

        root.left = null;
        root.right = null;
    }

    private void copyToList(TreeNode root) {
        if (root == null) return;

        result.add(root);
        copyToList(root.left);
        copyToList(root.right);
    }
}
