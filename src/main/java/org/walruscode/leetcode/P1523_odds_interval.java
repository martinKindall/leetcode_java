package org.walruscode.leetcode;

public class P1523_odds_interval {

    public int countOdds(int low, int high) {
        int odds = 0;

        if (low % 2 != 0 || high % 2 != 0) odds++;

        return odds + (high - low) / 2;
    }
}
