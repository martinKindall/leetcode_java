package org.walruscode.leetcode.medium;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.*;

public class P236_lowest_common_ancestor_bt {


    private static class Struct {
        int level;
        TreeNode root;
        boolean tracking;
        boolean finished;

        Struct(int level, TreeNode root, boolean tracking, boolean finished) {
            this.level = level;
            this.root = root;
            this.tracking = tracking;
            this.finished = finished;
        }

        Struct() {
            this.tracking = false;
            this.finished = false;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        int level = 0;

        Struct result = lowestCommonAncestorAux(root, p, q, 0, new Struct());

        return result.root;
    }

    private Struct lowestCommonAncestorAux(TreeNode root, TreeNode p, TreeNode q, int level, Struct result) {
        if (root == null) return result;

        Struct left = lowestCommonAncestorAux(root.left, p, q, level + 1, result);

        if (left.finished) return left;

        boolean coincidence = root == p || root == q;

        if (left.tracking) {
            Struct aux = level < left.level ?
                    new Struct(level, root, !coincidence, coincidence) :
                    new Struct(left.level, left.root, !coincidence, coincidence);

            if (coincidence) return aux;

            return lowestCommonAncestorAux(root.right, p, q, level + 1, aux);
        }

        if (coincidence) {
            Struct aux = new Struct(level, root, true, false);
            return lowestCommonAncestorAux(root.right, p, q, level + 1, aux);
        }

        return lowestCommonAncestorAux(root.right, p, q, level + 1, left);
    }

    private TreeNode result;
    private int level;
    private boolean track;

    public TreeNode lowestCommonAncestorStateful(TreeNode root, TreeNode p, TreeNode q) {
        level = Integer.MAX_VALUE;
        track = false;

        traverse(root, p, q, 0);

        return result;
    }

    private boolean traverse(TreeNode root, TreeNode p, TreeNode q, int level) {
        if (root == null) return false;

        boolean left = traverse(root.left, p, q, level + 1);

        if (left) return true;

        if (track && level < this.level) {
            result = root;
            this.level = level;
        }

        if (track && (root == p || root == q)) {
            track = false;

            return true;
        } else if (!track && (root == p || root == q)) {
            track = true;

            if (level < this.level) {
                result = root;
                this.level = level;
            }
        }

        return traverse(root.right, p, q, level + 1);
    }

    private static class Pair {
        TreeNode root;
        int level;

        Pair(TreeNode root, int level) {
            this.root = root;
            this.level = level;
        }
    }

    private static class Structure {
        Pair root;
        List<Pair> ancestors;

        Structure(Pair root, List<Pair> ancestors) {
            this.root = root;
            this.ancestors = ancestors;
        }
    }

    public TreeNode lowestCommonAncestorExceedsMemory(TreeNode root, TreeNode p, TreeNode q) {

        Stack<Structure> myStack = new Stack<>();
        Pair initial = new Pair(root, 0);

        Map<TreeNode, Structure> results = new HashMap<>();

        Structure initialStruct = new Structure(initial, List.of(initial));
        myStack.push(initialStruct);
        results.put(root, initialStruct);

        while (!myStack.isEmpty()) {

            Structure current = myStack.pop();

            int level = current.root.level;

            if (current.root == null) continue;

            if (current.root.root.right != null) {
                List<Pair> newAncestors = new ArrayList<>(current.ancestors);
                Pair curr = new Pair(current.root.root.right, level+1);
                newAncestors.add(curr);

                Structure struc = new Structure(curr, newAncestors);
                results.put(current.root.root.right, struc);

                myStack.push(struc);
            }

            if (current.root.root.left != null) {
                List<Pair> newAncestors = new ArrayList<>(current.ancestors);
                Pair curr = new Pair(current.root.root.left, level+1);
                newAncestors.add(curr);

                Structure struc = new Structure(curr, newAncestors);
                results.put(current.root.root.left, struc);

                myStack.push(struc);
            }
        }

        List<Pair> pAncestors = results.get(p).ancestors;
        List<Pair> qAncestors = results.get(q).ancestors;

        int pIdx = pAncestors.size() - 1;
        int qIdx = qAncestors.size() - 1;

        while (pIdx >= 0 && qIdx >= 0) {
            Pair pPair = pAncestors.get(pIdx);
            Pair qPair = qAncestors.get(qIdx);

            if (pPair.root == qPair.root) return pPair.root;

            else if (pPair.level < qPair.level) {
                qIdx--;
            } else if (pPair.level > qPair.level) {
                pIdx--;
            } else {
                qIdx--;
                pIdx--;
            }
        }

        return null;
    }
}
