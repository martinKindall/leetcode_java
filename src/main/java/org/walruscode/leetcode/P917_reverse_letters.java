package org.walruscode.leetcode;

public class P917_reverse_letters {

    public String reverseOnlyLetters(String s) {
        if (s.length() == 1) return s;

        char[] word = s.toCharArray();

        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (!isEnglishLetter(word[i])) {
                i++;
                continue;
            }
            if (!isEnglishLetter(word[j])) {
                j--;
                continue;
            }
            if (i != j) {
                char temp = word[i];
                word[i] = word[j];
                word[j] = temp;
                i++;
                j--;
            }
        }

        return new String(word);
    }

    boolean isEnglishLetter(int letter) {
        return letter >= 97 && letter <= 122 || letter >= 65 && letter <= 90;
    }
}
