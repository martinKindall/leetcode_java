package org.walruscode.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class P994_rotting_oranges {

    class SolutionBFSv2 {

        private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

        public int orangesRotting(int[][] grid) {

            if (grid == null) {
                throw new IllegalArgumentException("Input grid is null");
            }
            if (grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            Queue<Integer> queue = new LinkedList<>();

            int m = grid.length;
            int n = grid[0].length;

            int freshOranges = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 2) {
                        queue.add(i*n + j);
                    } else if (grid[i][j] == 1) {
                        freshOranges++;
                    }
                }
            }

            int minutes = 0;

            while (freshOranges != 0 && !queue.isEmpty()) {
                int size = queue.size();
                minutes++;

                for (int i = 0; i < size && freshOranges != 0; i++) {
                    int current = queue.remove();
                    int row = current / n;
                    int col = current % n;

                    for (int[] dir: DIRS) {
                        int x = row + dir[0];
                        int y = col + dir[1];

                        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1) continue;

                        grid[x][y] = 0;

                        freshOranges--;

                        if (freshOranges == 0) break;

                        queue.add(x*n + y);
                    }
                }
            }

            return freshOranges != 0 ? -1 : minutes;
        }
    }

    class SolutionDFS {

        public int orangesRotting(int[][] grid) {

            int m = grid.length;
            int n = grid[0].length;

            int minutes = 2;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 2) rottenNearby(grid, i, j, m, n, minutes);
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) return -1;
                    minutes = Math.max(minutes, grid[i][j]);
                }
            }

            return minutes - 2;
        }

        private void rottenNearby(int[][] grid, int i, int j, int m, int n, int minutes) {
            if (i < 0 || j < 0 || i >= m || j >= n ||
                    grid[i][j] == 0 ||
                    (1 < grid[i][j] && grid[i][j] < minutes)) // already rotten
                return;

            grid[i][j] = minutes;

            minutes++;

            rottenNearby(grid, i+1, j, m, n, minutes);
            rottenNearby(grid, i, j+1, m, n, minutes);
            rottenNearby(grid, i-1, j, m, n, minutes);
            rottenNearby(grid, i, j-1, m, n, minutes);
        }
    }

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
