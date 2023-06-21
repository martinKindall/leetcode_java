package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P345_reverse_vowels {

    public String reverseVowels(String s) {
        if (s.length() == 1) return s;

        char[] characters = s.toCharArray();

        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (isVowel(characters[i])) {
                while (j > i && !isVowel(characters[j])) {
                    j--;
                }
                if (isVowel(characters[j])) {
                    char temp = characters[i];
                    characters[i] = characters[j];
                    characters[j] = temp;
                    j--;
                    i++;
                }
            } else {
                i++;
            }
        }

        return new String(characters);
    }

    public String reverseVowelsV2(String s) {
        if (s.length() == 1) return s;

        List<Integer> occurrences = new ArrayList<>();

        Stack<Character> myStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                occurrences.add(i);
                myStack.add(s.charAt(i));
            }
        }

        char[] characters = s.toCharArray();

        for (Integer i: occurrences) {
            characters[i] = myStack.pop();
        }

        return new String(characters);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
