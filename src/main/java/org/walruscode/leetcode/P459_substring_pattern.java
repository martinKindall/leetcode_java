package org.walruscode.leetcode;

public class P459_substring_pattern {

    public boolean repeatedSubstringPattern(String s) {

        if (s.length() == 1) return false;

        int length = s.length();

        for (int i = length / 2; i >= 1; i--) {
            if (length % i == 0) {
                int times = length / i;
                String newS = s.substring(0, i);
                if (newS.repeat(times).equals(s)) return true;
            }
        }

        return false;
    }

    public boolean repeatedSubstringPatternOld(String s) {
        if (s.length() == 1) return false;

        int j = 2;

        while (s.length() / j > 0) {
            String newS = s.substring(0, s.length() / j);
            if (newS.repeat(j).equals(s)) return true;
            j++;
        }

        return false;
    }
}
