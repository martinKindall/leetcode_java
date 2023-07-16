package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class P46_permutations {

    List<List<Integer>> results;

    public List<List<Integer>> permute(int[] nums) {
        results = new ArrayList<>();

        backtrack(nums, new ArrayList<>());

        return results;
    }

    private void backtrack(int[] nums, List<Integer> current) {
        if (current.size() == nums.length) results.add(new ArrayList<>(current));
        else {
            for (int num: nums) {
                if (current.contains(num)) continue;

                current.add(num);
                backtrack(nums, current);

                current.remove(current.size() - 1);
            }
        }
    }
}
