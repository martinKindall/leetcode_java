package org.walruscode.leetcode;

public class P389_find_diff {

    public char findTheDifference(String s, String t) {
        int[] lettersS = new int['z' - 'a' + 1];
        int[] lettersT = new int['z' - 'a' + 1];

        for (char a: s.toCharArray()) {
            lettersS[a - 'a']++;
        }

        for (char a: t.toCharArray()) {
            lettersT[a - 'a']++;
        }

        for (char a: t.toCharArray()) {
            if (lettersT[a - 'a'] != lettersS[a - 'a']) return a;
        }

        throw new IllegalArgumentException("Wrong input");
    }
}
