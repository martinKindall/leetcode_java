package org.walruscode.leetcode;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.Stack;

public class P226_invert_tree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current == null || current.left == null && current.right == null) continue;

            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            stack.push(current.left);
            stack.push(current.right);
        }

        return root;
    }
}
