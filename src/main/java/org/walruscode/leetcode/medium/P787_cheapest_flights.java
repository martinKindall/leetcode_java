package org.walruscode.leetcode.medium;

import java.util.*;

public class P787_cheapest_flights {

    private record Tuple (int vertex, int cost, int stops) {}

    // Dijkstra
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<Node>[] adjList = new List[n];

        int[] totalCosts = new int[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            totalCosts[i] = Integer.MAX_VALUE;
        }

        for (int[] flight: flights) {
            adjList[flight[0]].add(new Node(flight[1], flight[2]));
        }

        totalCosts[src] = 0;

        // the key here is being greedy not with the costs, but with the stops.
        // this has a similar effect obtained doing BFS, where we go level by level (stop by stop)
        Queue<Tuple> queue = new PriorityQueue<>((u, v) -> u.stops - v.stops);

        queue.offer(new Tuple(src, 0, 0));

        while (!queue.isEmpty()) {
            Tuple current = queue.poll();

            for (Node neighbor: adjList[current.vertex]) {
                if (neighbor.cost + current.cost >= totalCosts[neighbor.to] || current.stops > k) continue;

                totalCosts[neighbor.to] = neighbor.cost + current.cost;

                queue.offer(new Tuple(neighbor.to, neighbor.cost + current.cost, current.stops + 1));
            }
        }

        if (totalCosts[dst] == Integer.MAX_VALUE) return -1;

        return totalCosts[dst];
    }

    // BFS approach, each level is 1 stop!
    public int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int k) {
        List<Node>[] adjList = new List[n];

        int[] totalCosts = new int[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            totalCosts[i] = Integer.MAX_VALUE;
        }

        for (int[] flight: flights) {
            adjList[flight[0]].add(new Node(flight[1], flight[2]));
        }

        totalCosts[src] = 0;

        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(src, 0));

        int level = 0;

        while (!queue.isEmpty() && level <= k) {
            int size = queue.size();

            while (size-- > 0) {
                Node current = queue.poll();

                for (Node neighbor: adjList[current.to]) {
                    if (neighbor.cost + current.cost >= totalCosts[neighbor.to]) continue;

                    totalCosts[neighbor.to] = neighbor.cost + current.cost;

                    // The key here is that we keep using the current sum neighbor.cost + current.cost
                    // for the rest of this particular travel.
                    // My previous dijkstra attempt failed because I was reading this cost directly from the
                    // costs array which is updated after each iteration, thus it reflects the min cost overall

                    // My previous attempt also failed because my Dijkstra implementation did greedy choices which
                    // didn't take into account the number of stops that were allowed, and hence it updated the costs
                    // even if that travel wasn't going to finish

                    queue.offer(new Node(neighbor.to, neighbor.cost + current.cost));
                }
            }

            level++;
        }

        if (totalCosts[dst] == Integer.MAX_VALUE) return -1;

        return totalCosts[dst];
    }

    private int minCost = Integer.MAX_VALUE;

    public int findCheapestPriceExceedsTime(int n, int[][] flights, int src, int dst, int k) {

        List<Node>[] adjList = new List[n];

        int[] totalCosts = new int[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            totalCosts[i] = Integer.MAX_VALUE;
        }

        for (int[] flight: flights) {
            adjList[flight[0]].add(new Node(flight[1], flight[2]));
        }

        boolean[] visited = new boolean[n];

        visited[src] = true;

        backtracking(src, dst, 0, -1, k, adjList, visited);

        if (minCost == Integer.MAX_VALUE) return -1;

        return minCost;
    }

    private void backtracking(int src, int dst, int accumCost, int connections, int k, List<Node>[] adjList, boolean[] visited) {
        if (connections > k) return;

        if (src == dst) {
            minCost = Math.min(minCost, accumCost);
            return;
        }

        for (Node neighbor: adjList[src]) {
            if (visited[neighbor.to]) continue;

            visited[neighbor.to] = true;

            backtracking(neighbor.to, dst, neighbor.cost + accumCost, connections + 1, k, adjList, visited);

            visited[neighbor.to] = false;
        }
    }

    private record Node(int to, int cost) {}
    private record Stops(int from, int stops) {}

    // Dijkstra
    public int findCheapestPriceFailedAttempt(int n, int[][] flights, int src, int dst, int k) {

        List<Node>[] adjList = new List[n];

        int[] totalCosts = new int[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            totalCosts[i] = Integer.MAX_VALUE;
        }

        for (int[] flight: flights) {
            adjList[flight[0]].add(new Node(flight[1], flight[2]));
        }

        totalCosts[src] = 0;

        Queue<Stops> queue = new PriorityQueue<>((u, v) -> totalCosts[u.from] - totalCosts[v.from]);

        queue.offer(new Stops(src, -1));

        int level = -1;
        int levelSize = queue.size();
        int currVertex = 0;

        while (!queue.isEmpty()) {

            Stops current = queue.poll();

            for (Node neighbor: adjList[current.from]) {
                int total = neighbor.cost + totalCosts[current.from];

                if (total >= totalCosts[neighbor.to]) continue;
                if (current.stops > k) continue;
                if (current.stops + 1 == k && neighbor.to != dst) continue;

                totalCosts[neighbor.to] = total;

                queue.offer(new Stops(neighbor.to, current.stops + 1));
            }

            currVertex++;

            if (currVertex == levelSize) {
                level++;

                if (level == k) break;

                levelSize = queue.size();

                currVertex = 0;
            }
        }

        if (totalCosts[dst] == Integer.MAX_VALUE) return -1;

        return totalCosts[dst];
    }
}
