package org.walruscode.leetcode;

import java.util.Stack;

public class P104_max_depth_tree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int maxHeight = 1;

        Stack<TreeNodeHeight> stack = new Stack<>();

        stack.push(new TreeNodeHeight(root, maxHeight));

        while (!stack.empty()) {

            TreeNodeHeight current = stack.pop();
            if (current.root == null) continue;

            if (current.height > maxHeight) maxHeight = current.height;

            stack.push(new TreeNodeHeight(current.root.left, current.height + 1));
            stack.push(new TreeNodeHeight(current.root.right, current.height + 1));
        }

        return maxHeight;
    }

    static class TreeNodeHeight {
        TreeNode root;
        int height;

        TreeNodeHeight(TreeNode root, int height) {
            this.root = root;
            this.height = height;
        }
    }


    public int maxDepthV2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
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
