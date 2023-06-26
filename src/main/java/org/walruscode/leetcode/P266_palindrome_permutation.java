package org.walruscode.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P266_palindrome_permutation {

    public boolean canPermutePalindrome(String s) {
        // palindrome strings have something in common
        // either all of their characters are repeated an even amount of times
        // or previous rule + 1 character is repeated an odd number of times

        if (s.length() == 1) return true;

        Map<Character, Integer> myMap = new HashMap<>();

        for (Character c: s.toCharArray()) {
            myMap.put(c, myMap.computeIfAbsent(c, (__) -> 0) + 1);
        }

        int odds = 0;

        for (Integer count: myMap.values()) {
            if (count % 2 != 0) odds++;
            if (odds > 1) return false;
        }

        return true;
    }
}
