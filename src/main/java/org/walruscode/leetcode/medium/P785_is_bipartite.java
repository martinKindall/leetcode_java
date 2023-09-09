package org.walruscode.leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class P785_is_bipartite {

    public boolean isBipartite(int[][] graph) {
        if (graph.length == 1 || graph.length == 2) return true;

        // colors, true: red, false: blue
        Map<Integer, Boolean> colors = new HashMap<>();

        int startingVertex = 0;

        // Full BFS
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length != 0) {
                startingVertex = i;

                if (colors.get(startingVertex) == null) {
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(startingVertex);
                    colors.put(startingVertex, true);

                    while (!queue.isEmpty()) {
                        int current = queue.poll();
                        Boolean currentColor = colors.get(current);

                        if (currentColor == null) throw new IllegalArgumentException("Current color should exist");

                        for (int neighbor: graph[current]) {
                            Boolean neighborColor = colors.get(neighbor);

                            if (neighborColor == currentColor) return false;

                            if (neighborColor == null) {
                                colors.put(neighbor, !currentColor);
                                queue.add(neighbor);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
