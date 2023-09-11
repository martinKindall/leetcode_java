package org.walruscode.leetcode.theory.dynamic_prog;

public class CutRod {

    // the index represents the length of the rod
    private static int[] prices = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};


    public static void main(String[] args) {

        for (int i = 1; i < prices.length; i++) {
            System.out.println(new CutRod().cutRotBottomUp(i));
            // System.out.println(new CutRod().cutRodTopBottom(i));
        }
    }

    private int cutRotBottomUp(int size) {
        int[] profit = new int[size+1];

        for (int i = 0; i < size+1; i++) {
            profit[i] = Integer.MIN_VALUE;
        }

        profit[0] = 0;

        for (int i = 1; i < size+1; i++) {

            int gain = Integer.MIN_VALUE;

            for (int j = 1; j <= i; j++) {

                // this is the step where we decide where to cut.
                // j = i means there's no cut, hence profit[0] = 0 and prices[j] is the price of the whole rod
                gain = Math.max(gain, prices[j] + profit[i - j]);
            }

            profit[i] = gain;
        }

        return profit[size];
    }


    private int cutRodTopBottom(int size) {
        int[] profit = new int[size+1];

        for (int i = 0; i < size+1; i++) {
            profit[i] = Integer.MIN_VALUE;
        }

        return cutRodAux(profit, size);
    }

    private int cutRodAux(int[] profit, int size) {

        if (profit[size] >= 0) return profit[size];

        if (size == 0) profit[0] = 0;
        else {
            int gain = Integer.MIN_VALUE;

            for (int i = 1; i <= size; i++) {
                gain = Math.max(gain, prices[i] + cutRodAux(profit, size - i));
            }

            profit[size] = gain;
        }

        return profit[size];
    }
}
