package org.walruscode.leetcode.medium;

public class P200_number_of_islands {

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int islands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    backtracking(grid, m, n, i, j);
                    islands++;
                }
            }
        }

        return islands;
    }

    private void backtracking(char[][] grid, int m, int n, int i, int j) {
        if (i == m || i == -1 || j == n || j == -1) return;

        char mark = 'x';

        if (grid[i][j] == '1') {
            grid[i][j] = mark;
        } else if (grid[i][j] == '0' || grid[i][j] == mark) {
            return;
        }

        if (i < m) {
            backtracking(grid, m, n, i + 1, j);
        }

        if (i > 0) {
            backtracking(grid, m, n, i - 1, j);
        }

        if (j < n) {
            backtracking(grid, m, n, i, j + 1);
        }

        if (j > 0) {
            backtracking(grid, m, n, i, j - 1);
        }
    }
}
