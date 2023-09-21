package org.walruscode.leetcode.medium;

import java.util.*;

public class P841_keys_rooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>(rooms.size());

        visited.add(0);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            for (Integer key: rooms.get(current)) {
                if (!visited.contains(key)) {
                    visited.add(key);
                    queue.offer(key);
                }
            }
        }

        return visited.size() == rooms.size();
    }
}
