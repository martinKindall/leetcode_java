package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P589_preorder {

    public List<Integer> preorder(Node root) {
        if (root == null) return List.of();
        if (root.children == null) return List.of(root.val);

        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(root);

        List<Integer> result = new ArrayList<>();

        while (!nodeStack.isEmpty()) {
            Node current = nodeStack.pop();
            if (current == null) continue;

            result.add(current.val);

            if (current.children != null) {
                for (int idx = current.children.size() - 1; idx >= 0; idx--) {
                    // I will assume get() takes O(1), otherwise I would use another
                    // stack to reverse the list
                    nodeStack.push(current.children.get(idx));
                }
            }
        }

        return result;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
