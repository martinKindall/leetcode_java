package org.walruscode.leetcode;

public class P942_DI_string {

    public int[] diStringMatch(String s) {
        int length = s.length();

        int[] result = new int[length + 1];

        int currMax = length;
        int currMin = 0;

        for (int i = 0; i < length; i++) {
            char current = s.charAt(i);

            if (current == 'I') {
                result[i] = currMin++;
            } else {
                result[i] = currMax--;
            }

            if (i + 1 == length) {
                result[i+1] = currMax;   // currMin would be the same
            }
        }

        return result;
    }
}
