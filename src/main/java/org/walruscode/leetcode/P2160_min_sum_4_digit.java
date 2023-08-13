package org.walruscode.leetcode;

import java.util.Arrays;

public class P2160_min_sum_4_digit {

    public int minimumSum(int num) {
        int n1 = num / 1000;
        int n2 = (num / 100) % 10;
        int n3 = (num / 10) % 10;
        int n4 = num % 10;

        int[] sorted = {n1, n2, n3, n4};
        Arrays.sort(sorted);

        // classic approach
        // return (sorted[0] * 10 + sorted[3]) + (sorted[1] * 10 + sorted[2]);

        // just 1 multiplication
        return (sorted[0] + sorted[1]) * 10 + sorted[2] + sorted[3];
    }
}
