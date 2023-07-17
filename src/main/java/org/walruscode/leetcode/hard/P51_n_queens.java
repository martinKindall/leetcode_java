package org.walruscode.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class P51_n_queens {

    List<List<String>> results;
    private boolean[] posDiag;
    private boolean[] negDiag;
    private boolean[] cols;

    public List<List<String>> solveNQueens(int n) {
        if (n == 1) return List.of(List.of("Q"));

        results = new ArrayList<>();
        posDiag = new boolean[2*n-1];
        negDiag = new boolean[2*n-1];
        cols = new boolean[n];

        backtrack(n, 0, 0, new ArrayList<>());

        return results;
    }

    private void backtrack(int n, int row, int queens, List<String> current) {
        if (queens == n) results.add(new ArrayList<>(current));
        else {
            outer:
            for (int i = 0; i < n; i++) {
                StringBuilder partialSol = new StringBuilder();

                if (queenCheck(row, i, n)) continue outer;

                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        partialSol.append("Q");
                        posDiag[row+i] = true;
                        negDiag[row-i+n-1] = true;
                        cols[i] = true;
                    } else {
                        partialSol.append(".");
                    }
                }
                current.add(partialSol.toString());
                backtrack(n, row + 1, queens + 1, current);
                current.remove(current.size() - 1);
                posDiag[row+i] = false;
                negDiag[row-i+n-1] = false;
                cols[i] = false;
            }
        }
    }

    // assuming x1 is always != x2 in this particular problem
    private boolean queenCheck(int x, int y, int n) {
        if (cols[y]) return true;

        if (posDiag[x+y]) return true;

        if (negDiag[x-y+n-1]) return true;

        return false;
    }
}
