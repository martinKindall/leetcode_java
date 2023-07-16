package org.walruscode.leetcode.medium;

import java.util.*;

public class P39_combination_sum {

    List<List<Integer>> results;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        results = new ArrayList<>();
        backtracking(target, candidates, 0, new ArrayList<>(), 0);

        return results;
    }

    private void backtracking(int target, int[] candidates, int idx, List<Integer> current, int partialSum) {
        if (partialSum == target) {
            results.add(new ArrayList<>(current));
            return;
        } else if (partialSum > target) return;

        for (int i = idx; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtracking(target, candidates, i, current, partialSum + candidates[i]);
            current.remove(current.size() - 1);
        }
    }
}
