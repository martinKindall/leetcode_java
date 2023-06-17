package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P228_summary_ranges {
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) return List.of();
        if (nums.length == 1) return List.of(nums[0] + "");

        var result = new ArrayList<String>();
        int prev = nums[0];
        int diff = 1;

        for (int i = 1; i < nums.length; i++) {
            if (prev + diff != nums[i]) {
                if (diff == 1) {
                    result.add(prev + "");
                } else {
                    result.add(prev + "->" + (prev + diff - 1));
                    diff = 1;
                }
                prev = nums[i];

            } else {
                diff++;
            }

            if (i + 1 == nums.length) {
                if (diff == 1) {
                    result.add(prev + "");
                } else {
                    result.add(prev + "->" + (prev + diff - 1));
                }
                break;
            }
        }

        return result;
    }
}
