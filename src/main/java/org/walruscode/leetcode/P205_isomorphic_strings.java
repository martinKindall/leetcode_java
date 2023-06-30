package org.walruscode.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P205_isomorphic_strings {

    /**
     * No need to find the whole structure of the string to represent the position
     * of each character. It is enough to store the last position of the current visited
     * character, and afterwards compare if the last position of both characters is the same.
     *
     * This is more performant if instead a Map, an integer array is used.
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() == 1) return true;
        if (s.length() != t.length()) return false;

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (sMap.get(s.charAt(i)) != null && !sMap.get(s.charAt(i)).equals(tMap.get(t.charAt(i))) ||
                    tMap.get(t.charAt(i)) != null && !tMap.get(t.charAt(i)).equals(sMap.get(s.charAt(i)))) return false;

            sMap.put(s.charAt(i), i + 1);
            tMap.put(t.charAt(i), i + 1);
        }

        return true;
    }
}
