package org.walruscode.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class P563_tree_tilt {

    public int findTilt(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNode> myStack = new Stack<>();
        myStack.push(root);

        Map<TreeNode, Integer> totalSums = new HashMap<>();

        while (!myStack.isEmpty()) {
            TreeNode current = myStack.pop();
            if (current == null) continue;
            if (current.right == null && current.left == null) {
                totalSums.put(current, current.val);
                continue;
            }

            if (current.left != null && !totalSums.containsKey(current.left) ||
            current.right != null && !totalSums.containsKey(current.right)) {
                myStack.push(current);
                myStack.push(current.right);
                myStack.push(current.left);
            } else {
                int sumLeft = totalSums.computeIfAbsent(current.left, (tree) -> 0);
                int sumRight = totalSums.computeIfAbsent(current.right, (tree) -> 0);
                totalSums.put(current, current.val + sumLeft + sumRight);
            }
        }

        myStack = new Stack<>();
        myStack.push(root);

        int result = 0;

        while (!myStack.isEmpty()) {
            TreeNode current = myStack.pop();

            if (current.left == null && current.right == null) continue;

            result += Math.abs(
                    totalSums.computeIfAbsent(current.left, (tree) -> 0) -
                    totalSums.computeIfAbsent(current.right, (tree) -> 0));

            if (current.left != null) {
                myStack.push(current.left);
            }

            if (current.right != null) {
                myStack.push(current.right);
            }
        }

        return result;
    }

    public int findTilt_slow(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNode> myStack = new Stack<>();
        myStack.push(root);

        int result = 0;

        while (!myStack.isEmpty()) {
            TreeNode current = myStack.pop();

            if (current.left == null && current.right == null) continue;

            result += Math.abs(sumTree(current.left) - sumTree(current.right));

            if (current.left != null) {
                myStack.push(current.left);
            }

            if (current.right != null) {
                myStack.push(current.right);
            }
        }

        return result;
    }

    private int sumTree(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNode> myStack = new Stack<>();
        myStack.push(root);

        int result = 0;

        while (!myStack.isEmpty()) {
            TreeNode current = myStack.pop();
            if (current == null) continue;
            result += current.val;
            myStack.push(current.left);
            myStack.push(current.right);
        }

        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
