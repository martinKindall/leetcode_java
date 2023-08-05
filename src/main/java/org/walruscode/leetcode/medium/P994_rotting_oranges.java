package org.walruscode.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class P994_rotting_oranges {

    class Solution {
        private boolean onlyFresh;
        private int maxMinutes;
        private Queue<Integer> queue;
        private int[][] copy;
        private int[][] isInQueue;

        public int orangesRotting(int[][] grid) {

            int m = grid.length;
            int n = grid[0].length;

            copy = new int[m][n];
            isInQueue = new int[m][n];

            queue = new LinkedList<>();

            onlyFresh = true;
            maxMinutes = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 || grid[i][j] == 2) {
                        scanForRotten(grid, i, j, m, n);

                        bfs(grid, m, n);

                        if (onlyFresh) return -1;
                        onlyFresh = true;
                    }
                }
            }

            return maxMinutes;
        }

        private void bfs(int[][] grid, int m, int n) {
            int size = queue.size();
            int minutes = 0;

            while (!queue.isEmpty()) {
                int current = queue.remove();
                size--;

                if (size == 0) {
                    minutes++;
                }

                int i = (int) Math.floor(current / n);
                int j = current % n;

                grid[i][j] = 0;

                if (i > 0 && grid[i-1][j] == 1 && isInQueue[i-1][j] == 0) {
                    queue.add((i-1)*n + j);
                    grid[i-1][j] = 0;
                }

                if (j > 0 && grid[i][j-1] == 1 && isInQueue[i][j-1] == 0) {
                    queue.add(i*n + j -1);
                    grid[i][j-1] = 0;
                }

                if (i + 1 < m && grid[i+1][j] == 1 && isInQueue[i+1][j] == 0) {
                    queue.add((i+1)*n + j);
                    grid[i+1][j] = 0;
                }

                if (j + 1 < n && grid[i][j+1] == 1 && isInQueue[i][j+1] == 0) {
                    queue.add(i*n + j + 1);
                    grid[i][j+1] = 0;
                }

                if (size == 0) {
                    size = queue.size();
                }
            }

            maxMinutes = Math.max(maxMinutes, minutes);
        }

        private void scanForRotten(int[][] grid, int i, int j, int m, int n) {
            if (copy[i][j] == 1) return;

            if (grid[i][j] == 2) {
                onlyFresh = false;

                grid[i][j] = 0;

                if (i > 0 && grid[i-1][j] == 1) {
                    queue.add((i-1)*n + j);
                    isInQueue[i-1][j] = 1;
                }

                if (j > 0 && grid[i][j-1] == 1) {
                    queue.add(i*n + j - 1);
                    isInQueue[i][j-1] = 1;
                }

                if (i + 1 < m && grid[i+1][j] == 1) {
                    queue.add((i+1)*n + j);
                    isInQueue[i+1][j] = 1;
                }

                if (j + 1 < n && grid[i][j+1] == 1) {
                    queue.add(i*n + j + 1);
                    isInQueue[i][j+1] = 1;
                }

                if (i > 0 && grid[i-1][j] != 0) {
                    scanForRotten(grid, i-1, j, m, n);
                }

                if (j > 0 && grid[i][j-1] != 0) {
                    scanForRotten(grid, i, j-1, m, n);
                }

                if (i + 1 < m && grid[i+1][j] != 0) {
                    scanForRotten(grid, i+1, j, m, n);
                }

                if (j + 1 < n && grid[i][j+1] != 0) {
                    scanForRotten(grid, i, j+1, m, n);
                }
            } else if (grid[i][j] == 1) {
                copy[i][j] = 1;

                if (i > 0) {
                    scanForRotten(grid, i-1, j, m, n);
                }

                if (j > 0) {
                    scanForRotten(grid, i, j-1, m, n);
                }

                if (i + 1 < m) {
                    scanForRotten(grid, i+1, j, m, n);
                }

                if (j + 1 < n) {
                    scanForRotten(grid, i, j+1, m, n);
                }
            }
        }
    }
}
