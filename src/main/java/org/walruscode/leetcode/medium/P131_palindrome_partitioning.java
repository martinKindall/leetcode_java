package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class P131_palindrome_partitioning {

    int[][] palindromeCache;
    List<List<String>> results;

    public List<List<String>> partition(String s) {
        if (s.length() == 1) return List.of(List.of(s));

        results = new ArrayList<>();
        palindromeCache = new int[s.length()+1][s.length()+1];

        backtracking(s, new ArrayList<>(), 0);

        return results;
    }

    private void backtracking(String s, List<String> current, int idx) {
        if (s.length() == idx) {
            results.add(new ArrayList<>(current));
            return;
        }

        for (int chunkSize = 1; chunkSize <= s.length() - idx; chunkSize++) {
            String curr = s.substring(idx, idx + chunkSize);

            if (palindromeCache[idx][idx+chunkSize] == 0) {
                palindromeCache[idx][idx+chunkSize] = isPalindrome(curr) ? 2 : 1;
            }

            if (palindromeCache[idx][idx+chunkSize] == 2) {
                current.add(curr);
                backtracking(s, current, idx+chunkSize);
                current.remove(current.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        if (s.length() == 1) return true;

        int i = 0;
        while (i < s.length() / 2) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
            i++;
        }

        return true;
    }
}
