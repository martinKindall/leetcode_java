package org.walruscode.leetcode;

import java.util.HashSet;
import java.util.Set;

public class P2367_number_triplets {

    public int arithmeticTriplets(int[] nums, int diff) {

        int result = 0;
        Set<Integer> visited = new HashSet<>();

        for (int num: nums) {
            if (visited.contains(num - diff) && visited.contains(num - diff * 2)) result++;
            visited.add(num);
        }

        return result;
    }

    public int arithmeticTripletsBrute(int[] nums, int diff) {
        int result = 0;

        boolean[] visited = new boolean[nums.length];

        Set<String> exists = new HashSet<>();

        for (int j = 0; j < nums.length - 1; j++) {
            int start = j;
            int found = 0;
            int inter = 0;

            if (visited[start]) continue;
            visited[start] = true;

            for (int i = j+1; i < nums.length; i++) {
                if (nums[i] - nums[start] > diff) {
                    found = 0;
                    start++;
                    if (visited[start]) break;
                    visited[start] = true;
                    i = start;
                }
                else if (nums[i] - nums[start] == diff) {
                    visited[i] = true;

                    if (found == 0) {
                        found = 1;
                        inter = start;
                        start = i;
                    } else if (found == 1) {
                        String triplet = "(" + inter + "," + start + "," + i + ")";
                        if (!exists.contains(triplet)) {
                            result++;
                            exists.add(triplet);
                        }

                        inter = start;
                        start = i;
                    }
                }
            }
        }

        return result;
    }
}
