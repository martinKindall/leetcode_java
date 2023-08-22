package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class P323_number_connected_components {

    // this is similar to number of islands.
    // a full DFS should be enough

    public int countComponents(int n, int[][] edges) {

        boolean[] visited = new boolean[n];

        int components = 0;

        List<Integer>[] adjList = new List[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);  // undirected
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                visited[i] = true;
                dfs(i, adjList, visited);
            }
        }

        return components;
    }

    private void dfs(int vertex, List<Integer>[] adjList, boolean[] visited) {
        for (Integer neighbor: adjList[vertex]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfs(neighbor, adjList, visited);
            }
        }
    }
}
