package org.walruscode.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17_phone_number {

    public List<String> letterCombinations(String digits) {
        digits = digits.replace("1", "");

        if (digits.length() == 0) return List.of();

        Map<Character, String[]> phone = new HashMap<>();
        phone.put('2', new String[]{"a", "b", "c"});
        phone.put('3', new String[]{"d", "e", "f"});
        phone.put('4', new String[]{"g", "h", "i"});
        phone.put('5', new String[]{"j", "k", "l"});
        phone.put('6', new String[]{"m", "n", "o"});
        phone.put('7', new String[]{"p", "q", "r", "s"});
        phone.put('8', new String[]{"t", "u", "v"});
        phone.put('9', new String[]{"w", "x", "y", "z"});

        if (digits.length() == 1) {
            return Arrays.stream(phone.get(digits.charAt(0))).toList();
        }

        String[] result = phone.get(digits.charAt(0));
        for (int i = 1; i < digits.length(); i++) {
            result = product(result, phone.get(digits.charAt(i)));
        }

        return Arrays.stream(result).toList();
    }

    private String[] product(String[] word1, String[] word2) {
        String[] result = new String[word1.length * word2.length];

        int z = 0;
        for (int i = 0; i < word1.length; i++) {
            for (int j = 0; j < word2.length; j++) {
                result[z++] = word1[i] + word2[j];
            }
        }

        return result;
    }
}
