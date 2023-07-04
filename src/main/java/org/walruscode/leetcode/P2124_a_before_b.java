package org.walruscode.leetcode;

public class P2124_a_before_b {

    public boolean checkString(String s) {

        boolean hasB = false;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (hasB) {
                if (current == 'a') return false;
            } else {
                if (current == 'b') hasB = true;
            }
        }

        return true;
    }
}
