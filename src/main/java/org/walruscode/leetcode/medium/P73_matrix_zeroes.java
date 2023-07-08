package org.walruscode.leetcode.medium;

public class P73_matrix_zeroes {

    public void setZeroes(int[][] matrix) {
        int[] markedRows = new int[200];
        int[] markedColumns = new int[200];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    markedRows[i] = 1;
                    markedColumns[j] = 1;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (markedRows[i] == 1 || markedColumns[j] == 1)
                    matrix[i][j] = 0;
            }
        }
    }
}
