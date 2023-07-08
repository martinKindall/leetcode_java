package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class P54_spiral_matrix {

    private final int STOP = 101;

    public List<Integer> spiralOrder(int[][] matrix) {

        int direction = 0;

        int i = 0, j = 0;

        List<Integer> result = new ArrayList<>();

        loop:
        while (i < matrix.length && i >= 0 && j < matrix[i].length && j >= 0) {
            result.add(matrix[i][j]);
            matrix[i][j] = STOP;

            switch (direction) {
                case 0 -> {
                    j++;
                    if (j == matrix[i].length) {
                        direction = 1;
                        j--;
                        i++;
                    } else if (matrix[i][j] == STOP) {
                        direction = 1;
                        j--;
                        i++;
                        if (matrix[i][j] == STOP) break loop;
                    }
                }
                case 1 -> {
                    i++;
                    if (i == matrix.length) {
                        direction = 2;
                        i--;
                        j--;
                    } else if (matrix[i][j] == STOP) {
                        direction = 2;
                        i--;
                        j--;
                        if (matrix[i][j] == STOP) break loop;
                    }
                }
                case 2 -> {
                    j--;
                    if (j == -1) {
                        direction = 3;
                        j++;
                        i--;

                        if (matrix[i][j] == STOP) break loop;
                    } else if (matrix[i][j] == STOP) {
                        direction = 3;
                        j++;
                        i--;
                        if (matrix[i][j] == STOP) break loop;
                    }
                }
                case 3 -> {
                    i--;
                    if (matrix[i][j] == STOP) {
                        direction = 0;
                        i++;
                        j++;
                        if (matrix[i][j] == STOP) break loop;
                    }
                }
                default -> {}
            }
        }

        return result;
    }
}
