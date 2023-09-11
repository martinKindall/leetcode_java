package org.walruscode.leetcode.medium;

import java.util.*;

public class P743_network_delay {

    record Node(int vertex, int time) {}

    public int networkDelayTime(int[][] times, int n, int k) {
        // Dijkstra

        List<Node>[] adjList = new List[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] time: times) {
            adjList[time[0]-1].add(new Node(time[1]-1, time[2]));
        }

        int[] accumWeight = new int[n];
        for (int i = 0; i < accumWeight.length; i++) {
            accumWeight[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> queue = new PriorityQueue<>((u, v) -> accumWeight[u] - accumWeight[v]);

        accumWeight[k-1] = 0;

        queue.offer(k-1);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Node neighbor: adjList[current]) {
                int time = neighbor.time + accumWeight[current];
                if (time >= accumWeight[neighbor.vertex]) continue;

                accumWeight[neighbor.vertex] = time;
                queue.offer(neighbor.vertex);
            }
        }

        int res = accumWeight[0];

        for (int time: accumWeight) {
            if (time == Integer.MAX_VALUE) return -1;

            if (time > res) res = time;
        }

        return res;
    }

    public int networkDelayTimeSlower(int[][] times, int n, int k) {
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
