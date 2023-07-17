package org.walruscode.leetcode.medium;

public class P79_word_search {
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;

        if (m*n < word.length())
            return false;

        // if one letter of the word is missing in the board, return immediately

        char[] wordArr = word.toCharArray();
        int[] charSet = new int[128];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ++charSet[board[i][j]];
            }
        }

        for (char ch : wordArr) {
            if (--charSet[ch] < 0) {
                return false;
            }
        }

        // start the backtracking

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j]) {
                    visited[i][j] = true;
                    boolean result = backtrack(board, word, board[i][j] + "", i, j);
                    if (result) return true;
                    visited[i][j] = false;
                }
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, String word, String current, int x, int y) {

        if (word.length() == current.length()) {
            return true;
        }

        if (x + 1 < board.length && !visited[x+1][y] && word.charAt(current.length()) == board[x+1][y]) {
            visited[x+1][y] = true;
            boolean result = backtrack(board, word, current + board[x+1][y], x+1, y);
            if (result) return true;
            visited[x+1][y] = false;
        }

        if (x - 1 >= 0 && !visited[x-1][y] && word.charAt(current.length()) == board[x-1][y]) {
            visited[x-1][y] = true;
            boolean result = backtrack(board, word, current + board[x-1][y], x-1, y);
            if (result) return true;
            visited[x-1][y] = false;
        }

        if (y + 1 < board[0].length && !visited[x][y+1] && word.charAt(current.length()) == board[x][y+1]) {
            visited[x][y+1] = true;
            boolean result = backtrack(board, word, current + board[x][y+1], x, y+1);
            if (result) return true;
            visited[x][y+1] = false;
        }

        if (y - 1 >= 0 && !visited[x][y-1] && word.charAt(current.length()) == board[x][y-1]) {
            visited[x][y-1] = true;
            boolean result = backtrack(board, word, current + board[x][y-1], x, y-1);
            if (result) return true;
            visited[x][y-1] = false;
        }

        return false;
    }
}
