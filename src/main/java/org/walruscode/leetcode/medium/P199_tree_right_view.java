package org.walruscode.leetcode.medium;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P199_tree_right_view {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return List.of();
        if (root.left == null && root.right == null) return List.of(root.val);

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);

        List<Integer> result = new ArrayList<>();

        while (!deque.isEmpty()) {
            int size = deque.size();
            boolean first = true;

            while (size-- > 0) {
                TreeNode current = deque.removeFirst();

                if (first) {
                    result.add(current.val);
                    first = false;
                }

                if (current.right != null) deque.add(current.right);
                if (current.left != null) deque.add(current.left);
            }
        }

        return result;
    }
}
