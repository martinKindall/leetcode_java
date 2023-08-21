package org.walruscode.leetcode.medium;

public class P277_find_celebrity {

    public int findCelebrity(int n) {
        int candidate = 0;

        // compares influencers and outputs the "biggest potential celebrity"
        for (int i = 0; i < n; i++) if (knows(candidate, i)) candidate = i;

        // check if the candidate knows others
        for (int i = 0; i < n; i++) if (candidate != i && knows(candidate, i)) return -1;

        // check if everyone knows the celebrity
        for (int i = 0; i < n; i++) if (!knows(i, candidate)) return -1;

        return candidate;
    }

    // idea: compute the inDegree for each number.
    // a celebrity has an inDegree of 0.

    public int findCelebrityV2(int n) {

        boolean[] isNotThatFamous = new boolean[n];

        outer:
        for (int i = 0; i < n; i++) {

            if (isNotThatFamous[i]) continue;

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                int result = knows(i, j) ? 1 : -1;

                if (result == 1) continue outer;
                else isNotThatFamous[j] = true;
            }

            for (int k = 0; k < n; k++) {
                if (k == i) continue;
                if (!knows(k, i)) return -1;
            }

            return i;
        }

        return -1;
    }

    // just to make the compiler happy
    private static boolean knows(int a, int b) {
        return true;
    }
}
