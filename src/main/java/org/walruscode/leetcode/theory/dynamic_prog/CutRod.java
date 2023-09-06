package org.walruscode.leetcode.theory.dynamic_prog;

public class CutRod {

    // the index represents the length of the rod
    private static int[] prices = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};


    public static void main(String[] args) {

        System.out.println(new CutRod().cutRod());
    }


    private int cutRod() {
        int[] profit = new int[prices.length];

        for (int i = 0; i < profit.length; i++) {
            profit[i] = Integer.MIN_VALUE;
        }

        return cutRodAux(profit, prices.length - 1);
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
