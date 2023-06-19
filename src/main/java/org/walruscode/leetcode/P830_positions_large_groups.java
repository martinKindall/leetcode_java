package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P830_positions_large_groups {

    public List<List<Integer>> largeGroupPositions(String s) {
        if (s.length() <= 2) return List.of();

        int count = 0;
        int start = -1;
        char current = '0';

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != current) {
                if (count >= 3) {
                    result.add(List.of(start, start + count - 1));
                }

                count = 1;
                start = i;
                current = s.charAt(i);
            } else {
                count++;
            }
        }

        if (count >= 3) {
            result.add(List.of(start, start + count - 1));
        }

        return result;
    }
}
