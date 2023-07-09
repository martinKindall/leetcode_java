package org.walruscode.leetcode;

public class P860_lemonade {

    public boolean lemonadeChange(int[] bills) {
        int bill5 = 0;
        int bill10 = 0;
        int bill20 = 0;

        for (int bill: bills) {
            if (bill == 20) {
                if (bill10 >= 1 && bill5 >= 1) {
                    bill10--;
                    bill5--;
                    bill20++;
                } else if (bill5 >= 3) {
                    bill5 -= 3;
                    bill20++;
                } else return false;
            } else if (bill == 10) {
                if (bill5 >= 1) {
                    bill5--;
                    bill10++;
                } else return false;
            } else if (bill == 5) {
                bill5++;
            }
        }

        return true;
    }
}
