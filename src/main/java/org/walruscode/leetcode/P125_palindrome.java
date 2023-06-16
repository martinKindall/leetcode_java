package org.walruscode.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class P125_palindrome {

    public boolean isPalindrome(String s) {
        if (s == null) return false;

        String cleaned = s.toLowerCase().replaceAll("[^a-z\\d]", "");

        if ("".equals(cleaned)) return true;

        for (int i = 0; i < (cleaned.length() / 2); i++) {
            if (cleaned.charAt(i) != cleaned.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
