package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class P684_redundant_connection {

    // brute force
    public int[] findRedundantConnection(int[][] edges) {

        int[] degrees = new int[edges.length+1];
        List<Integer>[] adj = new List[edges.length+1];

        for (int i = 0; i < edges.length + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;

            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] result = new int[2];

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            if (degrees[a] > 1 && degrees[b] > 1) {
                adj[a].remove(Integer.valueOf(b));  // no need to remove the symmetric edge, as we won't iterate over vertex b.

                boolean[] visited = new boolean[edges.length+1];
                visited[a] = true;

                boolean connected = dfs(adj, a, b, visited);

                if (connected) {
                    result[0] = a;
                    result[1] = b;
                }

                adj[a].add(b);
            }
        }

        return result;
    }

    private boolean dfs(List<Integer>[] adj, int start, int goal, boolean[] visited) {
        List<Integer> neighbors = adj[start];

        for (Integer neighbor: neighbors) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;

                if (neighbor == goal) return true;

                boolean connected = dfs(adj, neighbor, goal, visited);

                if (connected) return true;
            }
        }

        return false;
    }
}
