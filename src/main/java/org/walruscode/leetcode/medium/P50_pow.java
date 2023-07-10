package org.walruscode.leetcode.medium;

public class P50_pow {

    public double myPow(double x, int n) {

        if (x == 1.0) return 1.0;
        if (x == -1.0 && n % 2 == 0) return 1.0;
        if (x == -1.0 && n % 2 != 0) return -1.0;

        if (n < 0) {
            x = 1.0 / x;
        }

        double multiplier = x;
        double result = 1;

        while (n != 0) {
            if (n % 2.0 == 0) {
                multiplier *= multiplier;
                n >>= 1;   // equals to divide by 2
            } else {
                result *= multiplier;
                if (n > 0) n--;
                else n++;
            }
        }

        return result;
    }

    public double myPowOld(double x, int n) {
        if (x == 1.0) return 1.0;
        if (x == -1.0 && n % 2 == 0) return 1.0;
        if (x == -1.0 && n % 2 != 0) return -1.0;

        if (n < 0) {
            x = 1.0 / x;
            n = n * -1;
        }

        if (n == Integer.MIN_VALUE) {
            n = Integer.MAX_VALUE;
        }

        double multiplier = x;
        double result = 1;

        while (n > 0) {
            if (n % 2.0 == 0) {
                multiplier *= multiplier;
                n /= 2.0;
            } else {
                result *= multiplier;
                n--;
            }
        }

        return result;
    }
}
