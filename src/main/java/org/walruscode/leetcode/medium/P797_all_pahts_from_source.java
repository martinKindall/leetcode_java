package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class P797_all_pahts_from_source {

    private List<List<Integer>> results;

    // Backtracking
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        results = new ArrayList<>();

        List<Integer> initial = new ArrayList<>();
        initial.add(0);

        backtracking(graph, initial, 0, graph.length - 1);

        return results;
    }

    private void backtracking(int[][] graph, List<Integer> current, int from, int to) {
        if (from == to) {
            results.add(new ArrayList<>(current));

            return;
        }

        for (int neighbor: graph[from]) {
            current.add(neighbor);

            backtracking(graph, current, neighbor, to);

            current.remove(current.size()-1);
        }
    }
}
