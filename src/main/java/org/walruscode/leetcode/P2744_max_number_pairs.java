package org.walruscode.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P2744_max_number_pairs {

    public int maximumNumberOfStringPairs(String[] words) {
        if (words.length == 1) return 0;

        Map<String, Integer> myMap = new HashMap<>();

        for (String word: words) {
            myMap.put(word, myMap.computeIfAbsent(word, (__) -> 0) + 1);

            if (word.charAt(0) != word.charAt(1)) {
                String reversed = new StringBuilder(word).reverse().toString();
                myMap.put(reversed, myMap.computeIfAbsent(reversed, (__) -> 0) + 1);
            }
        }

        int pairs = 0;

        for (String word: words) {
            if (myMap.get(word) != null && myMap.get(word) >= 2) {
                pairs += myMap.get(word) / 2;
                myMap.remove(word);
                String reversed = new StringBuilder(word).reverse().toString();
                myMap.remove(reversed);
            }
        }

        return pairs;
    }
}
