package org.walruscode.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P242_anagram {

    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> letters = new HashMap<>();

        if (s.length() != t.length()) return false;

        for (int i = 0; i < s.length(); i++) {
            int sum = letters.computeIfAbsent(s.charAt(i), (value) -> 0);
            letters.put(s.charAt(i), ++sum);

            int sum2 = letters.computeIfAbsent(t.charAt(i), (value) -> 0);
            letters.put(t.charAt(i), --sum2);
        }

        for (Character key: letters.keySet()) {
            if (!letters.get(key).equals(0)) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagram2NSpace(String s, String t) {
        Map<Character, Integer> letters = new HashMap<>();
        Map<Character, Integer> lettersT = new HashMap<>();

        if (s.length() != t.length()) return false;

        for (int i = 0; i < s.length(); i++) {
            int sum = letters.computeIfAbsent(s.charAt(i), (value) -> 0);
            letters.put(s.charAt(i), ++sum);

            int sum2 = lettersT.computeIfAbsent(t.charAt(i), (value) -> 0);
            lettersT.put(t.charAt(i), ++sum2);
        }

        if (lettersT.keySet().size() != letters.keySet().size()) return false;

        for (Character key: lettersT.keySet()) {
            if (!lettersT.get(key).equals(letters.get(key))) {
                return false;
            }
        }

        return true;
    }
}
