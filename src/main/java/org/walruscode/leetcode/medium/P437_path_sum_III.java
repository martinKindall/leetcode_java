package org.walruscode.leetcode.medium;

import org.walruscode.leetcode.utils.TreeNode;

import java.util.HashMap;

public class P437_path_sum_III {

    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;

        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);

        return find_Sum(root, 0L, targetSum, map);
    }

    public int find_Sum(TreeNode curr, long sum, int target, HashMap<Long, Integer> map){
        if(curr == null) return 0;

        sum += curr.val;

        int result = 0;

        if (map.containsKey(sum - target)) result += map.get(sum - target);

        // this counting is important, because 6 == 6 + 0 == 6 - 5 + 5...

        map.put(sum, map.getOrDefault(sum, 0) + 1);

        result += find_Sum(curr.left, sum, target, map);
        result += find_Sum(curr.right, sum, target, map);

        map.put(sum, map.get(sum) - 1);

        return result;

    }

    // Is slower and uses less memory (but remember time is more valuable than memory)
    public int pathSumV2(TreeNode root, int targetSum) {
        if (root == null) return 0;

        return helper(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    private int helper(TreeNode root, long sum) {
        if (root == null) return 0;

        return (sum - root.val == 0 ? 1 : 0) + helper(root.left, sum - root.val) + helper(root.right, sum - root.val);
    }

    public int pathSumVeryFastButUsesMoreMemory(TreeNode root, int targetSum) {

        return (int) pathSumRec(root, targetSum, new long[0]);
    }

    private long pathSumRec(TreeNode root, int targetSum, long[] results) {
        if (root == null) return 0;

        int paths = 0;

        if (root.val == targetSum) paths++;

        long[] update = new long[results.length + 1];
        for (int i = 0; i < results.length; i++) {
            long partial = results[i] + root.val;
            update[i] = partial;

            if (partial == targetSum) paths++;
        }

        update[results.length] = (long) root.val;

        return paths +
                pathSumRec(root.left, targetSum, update) +
                pathSumRec(root.right, targetSum, update);
    }
}
