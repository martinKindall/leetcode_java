package org.walruscode.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P997_town_judge {

    public int findJudge(int n, int[][] trust) {
        if (n == 1) return 1;

        Map<Integer, Integer> counted = new HashMap<>();

        int currMaxVal = 0;
        int judge = -1;

        for (int[] ints : trust) {

            int sum = counted.getOrDefault(ints[1], 0) + 1;

            if (sum > currMaxVal) {
                judge = ints[1];
                currMaxVal = sum;
            }

            counted.put(ints[1], sum);
        }

        if (currMaxVal == n - 1) {

            // previously I used a Set to store the people that trust others
            // but it takes more space and appareantly is a bit slower, although
            // in theory should be faster, a set operation is O(1)
            for (int[] ints : trust) {
                if (ints[0] == judge) return -1;
            }
            return judge;
        }

        return -1;
    }
}
