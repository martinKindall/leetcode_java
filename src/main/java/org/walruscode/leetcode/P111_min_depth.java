package org.walruscode.leetcode;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.Stack;

public class P111_min_depth {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNodeHeight> stack = new Stack<>();
        stack.push(new TreeNodeHeight(root, 1));

        int result = Integer.MAX_VALUE;

        while (!stack.isEmpty()) {
            TreeNodeHeight current = stack.pop();
            if (current.root == null) continue;

            if (current.root.left == null && current.root.right == null) result = Math.min(result, current.height);
            else {
                stack.push(new TreeNodeHeight(current.root.right, current.height + 1));
                stack.push(new TreeNodeHeight(current.root.left, current.height + 1));
            }
        }

        return result;
    }

    class TreeNodeHeight {
        public int height;
        public TreeNode root;

        public TreeNodeHeight(TreeNode root, int height) {
            this.root = root;
            this.height = height;
        }
    }

    public int minDepthrecursive(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        if (root.left == null) {
            return 1 + minDepth(root.right);
        }

        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        int minLeft = minDepth(root.left);
        int minRight = minDepth(root.right);

        return 1 + Math.min(minLeft, minRight);
    }
}
