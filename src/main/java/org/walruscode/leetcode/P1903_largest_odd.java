package org.walruscode.leetcode;

public class P1903_largest_odd {
    public String largestOddNumber(String num) {
        int pointer = -1;

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '1' ||
                    num.charAt(i) == '3' ||
                    num.charAt(i) == '5' ||
                    num.charAt(i) == '7' ||
                    num.charAt(i) == '9') {
                pointer = i;
            }
        }

        if (pointer == -1) return "";

        return num.substring(0, pointer + 1);
    }
}
