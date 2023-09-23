package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P886_possible_bipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] colors = new int[n+1];

        List<Integer>[] adj = new List[n+1];

        for (int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < dislikes.length; i++) {
            adj[dislikes[i][0]].add(dislikes[i][1]);
            adj[dislikes[i][1]].add(dislikes[i][0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < colors.length; i++) {

            if (colors[i] != 0) continue;

            colors[i] = 1; // blue: 1, red: -1
            queue.offer(i);

            while (!queue.isEmpty()) {
                Integer current = queue.poll();

                for (int neighbor: adj[current]) {
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = colors[current] * -1;
                        queue.offer(neighbor);
                    }
                    else if (colors[neighbor] * colors[current] > 0)
                        return false;
                }
            }
        }

        return true;
    }
}
