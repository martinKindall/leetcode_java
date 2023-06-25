package org.walruscode.leetcode;

public class P2027_moves_convert {

    public int minimumMoves(String s) {

        int moves = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X') {
                moves++;
                i += 2;
            }
        }

        return moves;
    }
}
