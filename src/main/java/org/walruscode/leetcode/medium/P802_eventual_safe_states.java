package org.walruscode.leetcode.medium;

import java.util.*;

public class P802_eventual_safe_states {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        boolean[] isBad = new boolean[n];
        boolean[] isSafe = new boolean[n];

        for (int i = n - 1; i >= 0; i--) {
            if (!isBad[i] && !isSafe[i]) {
                dfs(isBad, isSafe, graph, i);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (isSafe[i])
                result.add(i);
        return result;
    }

    private boolean dfs(boolean[] isBad, boolean[] isSafe, int[][] graph, int current) {
        isBad[current] = true;

        for (int neighbor: graph[current]) {
            if (!isSafe[neighbor] && (isBad[neighbor] || !dfs(isBad, isSafe, graph, neighbor)))
                return false;
        }

        isBad[current] = false;
        isSafe[current] = true;

        return true;
    }

    public List<Integer> eventualSafeNodesKahn(int[][] graph) {
        int[] outDegrees = new int[graph.length];
        List<Integer>[] inverted = new List[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            outDegrees[i] = graph[i].length;
            inverted[i] = new ArrayList<>();
        }

        for (int i = 0; i < graph.length; i++) {
            for (int neighbor: graph[i]) {
                inverted[neighbor].add(i);
            }

            if (outDegrees[i] == 0) queue.offer(i);
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            result.add(current);

            for (Integer neighbor: inverted[current]) {

                if (--outDegrees[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        Collections.sort(result);

        return result;
    }

    public List<Integer> eventualSafeNodesDfsSlow(int[][] graph) {

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
                dfsOld(i, graph, visited, result);
            }
        }

        List<Integer> resultSort = new ArrayList<>(result);
        Collections.sort(resultSort);

        return resultSort;
    }

    private boolean dfsOld(int current, int[][] graph, boolean[] visited, Set<Integer> result) {

        for (int neighbor: graph[current]) {
            if (result.contains(neighbor)) continue;
            else if (visited[neighbor]) {
                return false;
            }
            else {
                visited[neighbor] = true;
                boolean isSafe = dfsOld(neighbor, graph, visited, result);

                if (!isSafe) return false;
            }
        }

        result.add(current);

        return true;
    }
}
