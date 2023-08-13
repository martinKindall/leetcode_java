package org.walruscode.leetcode;

public class P2652_sum_multiples {

    public int sumOfMultiples(int n) {

        int sum = 0;

        for (int i = 3; i <= n; i++) {
            sum += i % 3 == 0 || i % 5 == 0 || i % 7 == 0 ? i : 0;
        }

        return sum;
    }
}
