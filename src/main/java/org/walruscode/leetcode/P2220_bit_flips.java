package org.walruscode.leetcode;

public class P2220_bit_flips {

    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;

        return countOnes(xor);
    }

    // Kernighan's Algorithm
    int countOnes(int num) {
        int ones = 0;

        while (num > 0) {
            num = num & (num-1);
            ones++;
        }

        return ones;
    }

    int countOnesOld(int num) {
        int ones = 0;

        while (num > 0) {
            if (num % 2 != 0) {
                ones++;
                num--;
            } else {
                num /= 2;
            }
        }

        return ones;
    }
}
