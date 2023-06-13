package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P590_N_ary_tree {

    public List<Integer> postorder(Node root) {
        if (root == null) return List.of();
        if (root.children == null || root.children.isEmpty()) return List.of(root.val);

        Stack<Node> nodes = new Stack<>();
        nodes.push(root);

        List<Integer> result = new ArrayList<>();

        while (!nodes.isEmpty()) {
            Node current = nodes.pop();
            if (current == null) continue;

            if (current.children == null || root.children.isEmpty()) {
                result.add(current.val);
            } else {
                Stack<Node> auxStack = new Stack<>();

                nodes.push(new Node(current.val));

                for (Node node: current.children) {
                    auxStack.push(node);
                }
                for (Node __: current.children) {
                    nodes.push(auxStack.pop());
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
