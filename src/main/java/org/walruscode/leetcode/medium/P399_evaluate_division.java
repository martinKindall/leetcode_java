package org.walruscode.leetcode.medium;

import java.util.*;

public class P399_evaluate_division {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> weightedEdges = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);

            weightedEdges.computeIfAbsent(equation.get(0), k -> new HashMap<>()).put(equation.get(1), values[i]);
            weightedEdges.computeIfAbsent(equation.get(1), k -> new HashMap<>()).put(equation.get(0), 1 / values[i]);
        }

        double[] results = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            List<String> current = queries.get(i);
            String top = current.get(0);
            String base = current.get(1);

            Map<String, Double> adjTop = weightedEdges.get(top);
            Map<String, Double> adjBase = weightedEdges.get(base);

            if (adjTop == null || adjBase == null) {
                results[i] = -1.0;
                continue;
            }
            if (top.equals(base)) {
                results[i] = 1.0;
                continue;
            }

            visited.add(top);

            results[i] = dfs(weightedEdges, 1, top, base, visited);

            visited = new HashSet<>();
        }

        return results;
    }

    private double dfs(Map<String, Map<String, Double>> weightedEdges, double currentResult, String top, String base, Set<String> visited) {
        Map<String, Double> adjTop = weightedEdges.get(top);

        for (String neighbor: adjTop.keySet()) {
            if (visited.contains(neighbor)) continue;

            visited.add(neighbor);
            double weight = adjTop.get(neighbor);

            if (base.equals(neighbor)) return currentResult * weight;

            double res = dfs(weightedEdges, currentResult * weight, neighbor, base, visited);

            if (res != -1.0) return res;
        }

        return -1.0;
    }
}
