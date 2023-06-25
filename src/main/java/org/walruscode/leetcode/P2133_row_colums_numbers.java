package org.walruscode.leetcode;

public class P2133_row_colums_numbers {

    public boolean checkValid(int[][] matrix) {
        if (matrix.length == 1) return true;

        int[] visited = new int[matrix.length + 1];
        int[] visited2 = new int[matrix.length + 1];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                if (visited[matrix[i][j]] != i) return false;
                visited[matrix[i][j]]++;

                if (visited2[matrix[j][i]] != i) return false;
                visited2[matrix[j][i]]++;
            }
        }

        return true;
    }
}
