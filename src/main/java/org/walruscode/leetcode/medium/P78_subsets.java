package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class P78_subsets {

    List<List<Integer>> results;

    public List<List<Integer>> subsets(int[] nums) {

        results = new ArrayList<>();

        backtrack(nums, results, new ArrayList<>(), 0);

        return results;
    }

    private void backtrack(int[] nums, List<List<Integer>> results, List<Integer> current, int idx) {
        results.add(new ArrayList<>(current));

        for (int i = idx; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, results, current, i + 1);
            current.remove(current.size() - 1);
        }
    }
}
