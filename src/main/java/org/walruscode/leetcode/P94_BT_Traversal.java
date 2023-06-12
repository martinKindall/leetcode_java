package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class P94_BT_Traversal {

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

  static class Solution {
      public List<Integer> inorderTraversal(TreeNode root) {
          if (root == null) return List.of();
          if (root.left == null && root.right == null) return List.of(root.val);

          Stack<TreeNode> stack = new Stack<>();
          stack.push(root);

          List<Integer> result = new ArrayList<>();

          while (!stack.isEmpty()) {
              TreeNode node = stack.pop();

              if (node != null) {
                  if (node.left == null && node.right == null) result.add(node.val);
                  else {
                      stack.push(node.right);
                      stack.push(new TreeNode(node.val));
                      stack.push(node.left);
                  }
              }
          }

          return result;
      }
  }
}
