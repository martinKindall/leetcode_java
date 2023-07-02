package org.walruscode.leetcode;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.Stack;

public class P530_difference_bst {

    public int getMinimumDifference(TreeNode root) {

        Stack<TreeNode> myStack = new Stack<>();
        myStack.push(root);

        int prev = -1;
        int min = Integer.MAX_VALUE;

        while (!myStack.isEmpty()) {
            TreeNode current = myStack.pop();

            if (current == null) continue;

            if (current.left == null && current.right == null) {
                if (prev == -1) prev = current.val;
                else {
                    int newMin = Math.abs(current.val - prev);
                    if (newMin < min) min = newMin;
                    prev = current.val;
                }
            }
            else {
                myStack.push(current.right);
                myStack.push(new TreeNode(current.val));
                myStack.push(current.left);
            }
        }

        return min;
    }
}
