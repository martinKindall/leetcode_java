package org.walruscode.leetcode;

public class P2373_largest_local_vals {

    public int[][] largestLocal(int[][] grid) {

        int localSize = grid.length - 2;
        int[][] result = new int[localSize][localSize];

        for (int i = 0; i < localSize; i++) {
            for (int j = 0; j < localSize; j++) {
                for (int x = i; x <= i + 2; x++) {
                    for (int y = j; y <= j + 2; y++) {
                        int current = grid[x][y];

                        if (current > result[i][j])
                            result[i][j] = current;
                    }
                }
            }
        }

        return result;
    }
}
