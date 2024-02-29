package org.walruscode.leetcode;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P145_binary_postorder_trav {

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return List.of();
        if (root.left == null && root.right == null) return List.of(root.val);

        Deque<TreeNode> queue = new LinkedList<>();

        queue.push(root);

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode top = queue.pop();

            if (top == null) continue;

            if (top.left == null && top.right == null) {
                result.add(top.val);
                continue;
            }

            queue.push(new TreeNode(top.val));

            if (top.left == null) {
                queue.push(top.right);
            } else if (top.right == null) {
                queue.push(top.left);
            } else {
                queue.push(top.right);
                queue.push(top.left);
            }
        }

        return result;
    }
}
