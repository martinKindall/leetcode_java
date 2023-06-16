package org.walruscode.leetcode;

public class P121_stocks {

    public int maxProfit(int[] prices) {
        if (prices.length == 1) return 0;

        int i = 0, j = 1, maxDiff = 0;

        while (j < prices.length) {
            if (prices[j] < prices[i]) {
                i = j;
            } else {
                int diff = prices[j] - prices[i];
                if (diff > maxDiff) maxDiff = diff;
            }
            ++j;
        }

        return maxDiff;
    }

    public int maxProfitBruteForce(int[] prices) {
        if (prices.length == 1) return 0;

        int maxDiff = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxDiff) {
                    maxDiff = prices[j] - prices[i];
                }
            }
        }

        return maxDiff;
    }
}
