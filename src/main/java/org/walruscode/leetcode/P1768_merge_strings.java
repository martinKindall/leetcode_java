package org.walruscode.leetcode;

public class P1768_merge_strings {

    public String mergeAlternately(String word1, String word2) {

        char[] result = new char[word1.length() + word2.length()];

        int i = 0, j = 0, z = 0;

        while (i < word1.length() && j < word2.length()) {
            result[z++] = word1.charAt(i++);
            result[z++] = word2.charAt(j++);
        }

        while (i < word1.length()) {
            result[z++] = word1.charAt(i++);
        }

        while (j < word2.length()) {
            result[z++] = word2.charAt(j++);
        }

        return new String(result);
    }
}
