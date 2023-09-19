package org.walruscode.leetcode.medium;

import java.util.*;

public class P802_eventual_safe_states {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        int[] inDegrees = new int[graph.length];
        int[] outDegrees = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            outDegrees[i] = graph[i].length;

            for (int neighbor: graph[i]) {
                inDegrees[neighbor]++;
            }
        }

        Set<Integer> result = new HashSet<>();

        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (outDegrees[i] == 0) {
                result.add(i);
                visited[i] = true;
            }
        }

        // full DFS
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, graph, visited, result);
            }
        }

        List<Integer> resultSort = new ArrayList<>(result);
        Collections.sort(resultSort);

        return resultSort;
    }

    private boolean dfs(int current, int[][] graph, boolean[] visited, Set<Integer> result) {

        int countSafe = 0;

        for (int neighbor: graph[current]) {
            if (result.contains(neighbor)) countSafe++;
            else if (visited[neighbor]) {
                break;
            }
            else {
                visited[neighbor] = true;
                boolean isSafe = dfs(neighbor, graph, visited, result);

                if (isSafe) countSafe++;
                else break;
            }
        }

        if (countSafe == graph[current].length) {
            result.add(current);

            return true;
        }

        return false;
    }
}
