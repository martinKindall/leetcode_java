package org.walruscode.leetcode.medium;

import java.util.*;

public class P310_minimum_height_trees {

    List<Integer>[] adjList;

    public List<Integer> findMinHeightTreesDfs(int n, int[][] edges) {
        if (n == 0) return List.of();
        if (n == 1) return List.of(0);

        adjList = new List[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }

        boolean[] seen = new boolean[n];

        List<Integer> longestPath = dfs(0, seen);

        seen = new boolean[n];
        longestPath = dfs(longestPath.get(0), seen);  // the element at position 0 is the furthest element from the initial node taken in the first DFS

        Set<Integer> elements = new HashSet<>();
        elements.add(longestPath.get(longestPath.size() / 2));
        elements.add(longestPath.get((longestPath.size() - 1) / 2));

        return new ArrayList<>(elements);
    }

    private List<Integer> dfs(int node, boolean[] seen) {
        if (seen[node]) return new ArrayList<>();

        List<Integer> longestPath = new ArrayList<>();
        seen[node] = true;

        for (Integer neighbor: adjList[node]) {
            if (!seen[neighbor]) {
                List<Integer> path = dfs(neighbor, seen);
                if (path.size() > longestPath.size()) longestPath = path;
            }
        }

        longestPath.add(node);

        return longestPath;
    }

    /**
     * We use Kahn's algorithm to find the center(s) of the graph.
     * Intuitively we know the center will yield the min height.
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 0) return List.of();
        if (n == 1) return List.of(0);

        List<Integer>[] adjList = new List[n];
        int[] degrees = new int[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);

            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            if (degrees[i] == 1)
                queue.add(i);
        }

        List<Integer> result = null;


        while (!queue.isEmpty()) {
            result = new ArrayList<>();

            int levelSize = queue.size();

            while (--levelSize >= 0) {
                int current = queue.poll();
                result.add(current);
                degrees[current]--;

                List<Integer> neighbors = adjList[current];

                for (Integer neighbor: neighbors) {
                    degrees[neighbor]--;

                    if (degrees[neighbor] == 1) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return result;
    }

    public List<Integer> findMinHeightTreesBrute(int n, int[][] edges) {

        List<Integer>[] adjList = new List[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }

        int minHeight = Integer.MAX_VALUE;
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            visited[i] = true;
            int height = dfsHeight(i, adjList, visited, minHeight, 1);

            if (height < minHeight) {
                minHeight = height;
                results = new ArrayList<>();
                results.add(i);
            } else if (height == minHeight) {
                results.add(i);
            }
        }

        return results;
    }

    private int dfsHeight(int vertex, List<Integer>[] adjList, boolean[] visited, int minHeight, int currHeight) {

        int skipped = 0;

        for (Integer node: adjList[vertex]) {
            if (visited[node])
                skipped++;
        }

        if (skipped == adjList[vertex].size()) return currHeight;

        if (currHeight >= minHeight) return Integer.MAX_VALUE;

        int heightChild = Integer.MIN_VALUE;

        for (Integer node: adjList[vertex]) {
            if (!visited[node]) {
                visited[node] = true;
                heightChild = Math.max(heightChild, dfsHeight(node, adjList, visited, minHeight, 1 + currHeight));
            }
        }

        return heightChild;
    }
}
