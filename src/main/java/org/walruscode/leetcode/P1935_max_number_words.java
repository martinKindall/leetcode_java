package org.walruscode.leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class P1935_max_number_words {

    public int canBeTypedWords(String text, String brokenLetters) {

        List<String> wordList = new ArrayList<>(Arrays.asList(text.split(" ")));

        AtomicInteger idx = new AtomicInteger();

        for (int i = 0; i < brokenLetters.length(); i++) {
            idx.set(i);
            wordList = wordList.stream().filter(word -> word.indexOf(brokenLetters.charAt(idx.get())) < 0).toList();
        }

        return wordList.size();
    }

    public int canBeTypedWordsV2(String text, String brokenLetters) {

        String[] words = text.split(" ");
        Set<String> wordsSet = new HashSet<>();

        for (String word: words) {
            wordsSet.add(word);
        }

        String modifiedText = text;

        for (int i = 0; i < brokenLetters.length(); i++) {
            modifiedText = modifiedText.replace(brokenLetters.charAt(i), '?');
        }

        int total = 0;

        for (String word: modifiedText.split(" ")) {
            if (wordsSet.contains(word)) total++;
        }

        return total;
    }

    public int canBeTypedWordsOld(String text, String brokenLetters) {

        String[] words = text.split(" ");
        int total = words.length;

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < brokenLetters.length(); j++) {
                if (words[i].indexOf(brokenLetters.charAt(j)) > -1) {
                    total--;
                    break;
                }
            }
        }

        return total;
    }
}
