package org.walruscode.leetcode;

public class P243_word_dist {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        if (wordsDict.length == 2) return 1;

        int marker1 = -1;
        int marker2 = -1;

        int distance = Integer.MAX_VALUE;

        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                marker1 = i;
            } else if (wordsDict[i].equals(word2)) {
                marker2 = i;
            }

            if (marker1 != -1 && marker2 != -1) {
                int newDist = Math.abs(marker1 - marker2);
                if (distance > newDist) {
                    distance = newDist;
                }
            }
        }

        return distance;
    }
}
