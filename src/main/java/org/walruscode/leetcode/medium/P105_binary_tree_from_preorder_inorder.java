package org.walruscode.leetcode.medium;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.*;

public class P105_binary_tree_from_preorder_inorder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inMap);

        return root;
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, Map<Integer, Integer> inMap) {
        if (preStart > preEnd) {
            return null;
        }

        int inIdx = inMap.get(preorder[preStart]);
        int leftNodes = inIdx - inStart;

        TreeNode node = new TreeNode(preorder[preStart]);
        node.left = buildTree(preorder, preStart + 1, preStart + leftNodes, inorder, inStart, inMap);
        node.right = buildTree(preorder, preStart + leftNodes + 1, preEnd, inorder, inIdx + 1, inMap);

        return node;
    }

    private int p = 0;
    private int i = 0;

    public TreeNode buildTreeRecursiveV2(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (p >= preorder.length) return null;

        if (inorder[i] == stop) {
            i++;
            return null;
        }

        TreeNode node = new TreeNode(preorder[p++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);

        return node;
    }

    public TreeNode buildTreeIterative(int[] preorder, int[] inorder) {
        if (preorder.length == 1) return new TreeNode(preorder[0]);

        Stack<TreeNode> someRoot = new Stack<>();

        TreeNode root = new TreeNode();
        TreeNode currRoot = root;

        TreeNode temp = new TreeNode();
        TreeNode currTemp = temp;

        int preIdx = 0;
        int inIdx = 0;

        Set<Integer> leftNumbers = new HashSet<>();

        while (preIdx < preorder.length) {
            if (preorder[preIdx] == inorder[inIdx]) {
                TreeNode t = new TreeNode(preorder[preIdx]);
                currTemp.left = t;

                currRoot.right = temp.left;

                currRoot = t;
                leftNumbers.add(preorder[preIdx]);

                temp = new TreeNode();
                currTemp = temp;

                inIdx++;
                while (inIdx < inorder.length) {
                    if (leftNumbers.contains(inorder[inIdx])) {
                        currRoot = someRoot.pop();
                        inIdx++;
                    } else {
                        break;
                    }
                }

                preIdx++;
            } else {
                TreeNode t = new TreeNode(preorder[preIdx]);
                leftNumbers.add(preorder[preIdx]);
                someRoot.push(t);
                currTemp.left = t;
                currTemp = currTemp.left;
                preIdx++;
            }
        }

        return root.right;
    }
}
