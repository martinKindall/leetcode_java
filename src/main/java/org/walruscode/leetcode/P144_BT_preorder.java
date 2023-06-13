package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P144_BT_preorder {

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return List.of();
        if (root.left == null && root.right == null) return List.of(root.val);

        Stack<TreeNode> myStack = new Stack<>();
        myStack.push(root);

        List<Integer> results = new ArrayList<>();

        while (!myStack.isEmpty()) {
            TreeNode current = myStack.pop();
            if (current == null) continue;

            results.add(current.val);

            if (current.right != null) {
                myStack.push(current.right);
            }

            if (current.left != null) {
                myStack.push(current.left);
            }
        }

        return results;
    }

    static class TreeNode {
        public int val;
        TreeNode left, right;
        TreeNode() {}

        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
