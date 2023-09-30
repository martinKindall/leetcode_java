package org.walruscode.leetcode.medium;

import java.util.*;

public class P947_stones_removed {

    private record RowCol(int row, int col) {}

    public int removeStones(int[][] stones) {

        int ans = stones.length;
        if (ans == 1) return 0;

        Map<Integer, List<RowCol>> rows = new HashMap<>();
        Map<Integer, List<RowCol>> cols = new HashMap<>();

        for (int[] stone: stones) {
            List<RowCol> row = rows.getOrDefault(stone[0], new ArrayList<>());
            row.add(new RowCol(stone[0], stone[1]));
            List<RowCol> col = cols.getOrDefault(stone[1], new ArrayList<>());
            col.add(new RowCol(stone[0], stone[1]));

            rows.put(stone[0], row);
            cols.put(stone[1], col);
        }

        Set<RowCol> visited = new HashSet<>();

        for (int i = 0; i < stones.length; i++) {
            if (!visited.contains(new RowCol(stones[i][0], stones[i][1]))) {
                dfsV2(stones, visited, stones[i][0], stones[i][1], rows, cols);
                ans--;
            }
        }

        return ans;
    }

    private void dfsV2(int[][] stones, Set<RowCol> visited, int x, int y, Map<Integer, List<RowCol>> rows, Map<Integer, List<RowCol>> cols) {
        visited.add(new RowCol(x, y));

        for (RowCol node: rows.get(x)) {
            if (!visited.contains(new RowCol(node.row, node.col))) {
                dfsV2(stones, visited, node.row, node.col, rows, cols);
            }
        }

        for (RowCol node: cols.get(y)) {
            if (!visited.contains(new RowCol(node.row, node.col))) {
                dfsV2(stones, visited, node.row, node.col, rows, cols);
            }
        }
    }

    public int removeStonesDfsSlow(int[][] stones) {
        int ans = stones.length;

        if (ans == 1) return 0;

        Set<RowCol> visited = new HashSet<>();

        for (int i = 0; i < stones.length; i++) {
            if (!visited.contains(new RowCol(stones[i][0], stones[i][1]))) {
                dfs(stones, visited, stones[i][0], stones[i][1]);
                ans--;
            }
        }

        return ans;
    }

    private void dfs(int[][] stones, Set<RowCol> visited, int x, int y) {
        visited.add(new RowCol(x, y));

        for (int i = 0; i < stones.length; i++) {
            if ((x == stones[i][0] || y == stones[i][1]) && !visited.contains(new RowCol(stones[i][0], stones[i][1]))) {
                dfs(stones, visited, stones[i][0], stones[i][1]);
            }
        }
    }
}
