package org.walruscode.leetcode.medium;

public class P547_number_of_provinces {

    // similar to number of islands?
    // launch a full BFS (or full DFS would do)
    // this is an undirected graph (see constraints)

    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;

        if (n == 1) return 1;

        boolean[] visited = new boolean[n];

        int provinces = 0;

        for (int i = 0; i < n; i++) {

            if (visited[i]) continue;

            visited[i] = true;

            provinces++;

            for (int j = 0; j < n; j++) {

                if (i == j) continue;

                if (isConnected[i][j] == 0) continue;

                dfs(isConnected, visited, j);
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {

        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 0) continue;

            if (visited[j]) continue;

            visited[j] = true;

            dfs(isConnected, visited, j);
        }
    }
}
