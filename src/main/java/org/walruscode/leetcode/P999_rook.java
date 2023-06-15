package org.walruscode.leetcode;

public class P999_rook {

    public int numRookCaptures(char[][] board) {
        char BISHOP = 'B';
        char PAWN = 'p';
        char EMPTY = '.';

        int totalPawns = 0;

        // find rook

        int x = 0, y = 0;

        findRook: for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;

                    break findRook;
                }
            }
        }

        // count pawns

        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y] == BISHOP) break;
            if (board[i][y] == PAWN) {
                totalPawns++;
                break;
            }
        }

        for (int i = x + 1; i < 8; i++) {
            if (board[i][y] == BISHOP) break;
            if (board[i][y] == PAWN) {
                totalPawns++;
                break;
            }
        }

        for (int j = y + 1; j < 8; j++) {
            if (board[x][j] == BISHOP) break;
            if (board[x][j] == PAWN) {
                totalPawns++;
                break;
            }
        }

        for (int j = y - 1; j >= 0; j--) {
            if (board[x][j] == BISHOP) break;
            if (board[x][j] == PAWN) {
                totalPawns++;
                break;
            }
        }

        return totalPawns;
    }
}
