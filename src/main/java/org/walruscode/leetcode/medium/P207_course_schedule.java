package org.walruscode.leetcode.medium;

import java.util.*;

public class P207_course_schedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) return true;
        if (numCourses == 0) return true;

        Map<Integer, List<Integer>> courses = new HashMap<>();

        for (int[] prereq: prerequisites) {
            List<Integer> previous = courses.getOrDefault(prereq[0], new ArrayList<>());
            previous.add(prereq[1]);
            courses.put(prereq[0], previous);
        }

        int[] degrees = new int[numCourses];
        for (List<Integer> courseList: courses.values()) {
            for (Integer course: courseList) {
                degrees[course]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int node = 0; node < numCourses; node++) {
            if (degrees[node] == 0) queue.add(node);
        }

        int nodesFound = 0;

        while (!queue.isEmpty()) {
            int node = queue.remove();
            nodesFound++;
            List<Integer> currentCourses = courses.get(node);
            if (currentCourses == null) continue;

            for (Integer course: currentCourses) {
                degrees[course]--;

                if (degrees[course] == 0) queue.add(course);
            }
        }

        return nodesFound == numCourses;
    }

    public boolean canFinishV2(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) return true;
        if (numCourses == 0) return true;

        Map<Integer, List<Integer>> courses = new HashMap<>();

        for (int[] prereq: prerequisites) {
            List<Integer> previous = courses.getOrDefault(prereq[0], new ArrayList<>());
            previous.add(prereq[1]);
            courses.put(prereq[0], previous);
        }

        Set<Integer> isFinishable = new HashSet<>();
        Stack<Integer> coursesStack = new Stack<>();

        Stack<Integer> currentPath = new Stack<>();

        for (Integer course: courses.keySet()) {
            coursesStack.push(course);
        }

        while (!coursesStack.isEmpty()) {
            Integer current = coursesStack.pop();
            currentPath.push(current);

            List<Integer> preq = courses.get(current);

            if (preq == null) {
                isFinishable.add(current);
                currentPath.pop();
                continue;
            }

            boolean hasNotFinished = false;

            for (Integer p: preq) {
                if (!isFinishable.contains(p)) {
                    coursesStack.push(current);
                    hasNotFinished = true;
                    break;
                }
            }

            if (hasNotFinished) {
                for (Integer p: preq) {
                    if (currentPath.contains(p)) return false;

                    if (isFinishable.contains(p)) continue;
                    coursesStack.push(p);
                }
            } else {
                isFinishable.add(current);
                while (!currentPath.isEmpty() && currentPath.peek() == current) currentPath.pop();
            }
        }

        return true;
    }
}
