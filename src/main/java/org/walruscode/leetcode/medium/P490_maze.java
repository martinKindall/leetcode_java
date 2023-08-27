package org.walruscode.leetcode.medium;

public class P490_maze {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        int[] current = new int[]{start[0], start[1]};

        return backtracking(maze, destination, visited, current);
    }


    private boolean backtracking(int[][] maze, int[] destination, boolean[][] visited, int[] current) {
        if (current[0] == destination[0] && current[1] == destination[1]) return true;
        if (visited[current[0]][current[1]]) return false;

        visited[current[0]][current[1]] = true;

        int originalX = current[0];
        int originalY = current[1];

        for (int i = 0; i < 4; i++) {  // we'll try 4 movements (left, right, up, down) in that order
            moveToDirection(i, maze, current);
            boolean res = backtracking(maze, destination, visited, current);

            if (res) return true;

            // 1 step backward
            current[0] = originalX;
            current[1] = originalY;
        }

        return false;
    }


    private void moveToDirection(int i, int[][] maze, int[] current) {
        int m = maze.length;
        int n = maze[0].length;

        switch (i) {
            case 0 -> {
                if (current[1] == 0) return;

                while (current[1] > 0 && maze[current[0]][current[1]-1] != 1) {
                    current[1]--;
                }
            }
            case 1 -> {
                if (current[1] == n - 1) return;

                while (current[1] < n - 1 && maze[current[0]][current[1]+1] != 1) {
                    current[1]++;
                }
            }
            case 2 -> {
                if (current[0] == 0) return;

                while (current[0] > 0 && maze[current[0]-1][current[1]] != 1) {
                    current[0]--;
                }
            }
            case 3 -> {
                if (current[0] == m - 1) return;

                while (current[0] < m - 1 && maze[current[0]+1][current[1]] != 1) {
                    current[0]++;
                }
            }
            default -> {}
        }
    }
}
