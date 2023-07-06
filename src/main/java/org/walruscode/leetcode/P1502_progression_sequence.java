package org.walruscode.leetcode;

import java.util.Arrays;

public class P1502_progression_sequence {

    public boolean canMakeArithmeticProgression(int[] arr) {

        if (arr.length <= 2) return true;

        Arrays.sort(arr);

        int prevDiff = arr[1] - arr[0];

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i-1] != prevDiff) return false;
            prevDiff = arr[i] - arr[i-1];
        }

        return true;
    }
}
