package org.walruscode.leetcode;

import java.util.Arrays;

public class P1652_defuse_bomb {

    public int[] decrypt(int[] code, int k) {
        if (k == 0) {
            Arrays.fill(code, 0);
            return code;
        }

        int[] result = new int[code.length];

        if (k > 0) {
            int init = 0;

            int j = 1;
            while (j <= k) {
                init += code[j];
                ++j;
            }

            for (int i = 0; i < code.length; i++) {
                result[i] = init;
                init -= code[(i+1) % code.length];
                init += code[(i+1+k) % code.length];
            }

            return result;
        }

        if (k < 0) {
            int init = 0;

            int j = code.length - 1;
            while (j >= code.length + k) {
                init += code[j];
                --j;
            }

            for (int i = 0; i < code.length; i++) {
                result[i] = init;
                init -= code[(code.length + k + i) % code.length];
                init += code[(code.length + i) % code.length];
            }

            return result;
        }

        throw new RuntimeException("This should not happen");
    }
}
