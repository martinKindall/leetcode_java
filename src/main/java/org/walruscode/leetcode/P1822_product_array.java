package org.walruscode.leetcode;

public class P1822_product_array {

    public int arraySign(int[] nums) {
        int negatives = 0;

        for (int num: nums) {
            if (num < 0) negatives++;
            else if (num == 0) return 0;
        }

        return negatives % 2 == 0 ? 1 : -1;
    }
}
