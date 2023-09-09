package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P743_network_delay {

    public int networkDelayTime(int[][] times, int n, int k) {
        // BFS, level sets

        int[] accumWeight = new int[n+1];
        boolean[] visited = new boolean[n+1];

        for (int i = 0; i < accumWeight.length; i++) {
            accumWeight[i] = Integer.MAX_VALUE;
        }

        accumWeight[k] = 0;

        List<List<Integer>>[] adj = new List[n+1];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] time: times) {
            adj[time[0]].add(List.of(time[1], time[2]));
        }

        Queue<List<Integer>> queue = new LinkedList<>();

        queue.add(List.of(k, 0));

        visited[k] = true;

        while (!queue.isEmpty()) {
            List<Integer> current = queue.poll();

            List<List<Integer>> neighbors = adj[current.get(0)];

            for (List<Integer> neighbor: neighbors) {
                int totalPathWeight = neighbor.get(1) + current.get(1);

                // relaxation step
                if (totalPathWeight < accumWeight[neighbor.get(0)]) {
                    accumWeight[neighbor.get(0)] = totalPathWeight;
                    queue.add(List.of(neighbor.get(0), accumWeight[neighbor.get(0)]));
                }

                visited[neighbor.get(0)] = true;
            }
        }

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) return -1;
        }

        int result = Integer.MIN_VALUE;

        for (int i = 1; i < accumWeight.length; i++) {
            result = Math.max(result, accumWeight[i]);
        }

        return result;
    }
}
