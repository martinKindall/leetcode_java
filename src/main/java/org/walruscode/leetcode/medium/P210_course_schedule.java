package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P210_course_schedule {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjacencyList = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int[] dep: prerequisites) {
            List<Integer> children = adjacencyList[dep[1]];
            children.add(dep[0]);
        }

        int[] inDegree = new int[numCourses];

        for (int[] dep: prerequisites) {
            inDegree[dep[0]]++;
        }

        return fullDFSV2(inDegree, adjacencyList, numCourses);
    }

    private int[] fullDFSV2(int[] inDegree, List<Integer>[] adjacencyList, int numCourses) {
        boolean[] visited = new boolean[numCourses];
        boolean[] ready = new boolean[numCourses];
        int[] result  = new int[numCourses];
        int total = 0;

        Stack<Integer> courses = new Stack<>();

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                courses.push(i);
            }
        }

        while (!courses.isEmpty()) {
            int current = courses.pop();

            if (ready[current]) continue;

            if (visited[current]) {
                result[total++] = current;
                ready[current] = true;
                continue;
            }

            visited[current] = true;

            List<Integer> folloUpCourses = adjacencyList[current];

            if (folloUpCourses.size() > 0) {
                courses.push(current);
                for (Integer course: folloUpCourses) {
                    if (!visited[course])
                        courses.push(course);

                        // cycle detection
                    else if (!ready[course]) return new int[0];
                }
            } else {
                result[total++] = current;
                ready[current] = true;
            }
        }

        reverse(result);

        return total == numCourses ? result : new int[0];
    }

    private void reverse(int[] arr) {
        int n = arr.length;

        for(int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }

    public int[] findOrderV2(int numCourses, int[][] prerequisites) {

        List<Integer>[] adjacencyList = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int[] dep: prerequisites) {
            List<Integer> children = adjacencyList[dep[1]];
            children.add(dep[0]);
        }

        int[] inDegree = new int[numCourses];

        for (int[] dep: prerequisites) {
            inDegree[dep[0]]++;
        }

        return fullDFS(inDegree, adjacencyList, numCourses);
    }

    private int[] fullDFS(int[] inDegree, List<Integer>[] adjacencyList, int numCourses) {

        int[] result  = new int[numCourses];
        int visited = 0;

        Stack<Integer> courses = new Stack<>();

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                courses.push(i);
                result[visited++] = i;
            }
        }

        while (!courses.isEmpty()) {
            int current = courses.pop();

            List<Integer> children = adjacencyList[current];

            for (Integer child: children) {
                inDegree[child]--;
                if (inDegree[child] == 0) {
                    courses.push(child);
                    result[visited++] = child;
                }
            }
        }

        return visited == numCourses ? result : new int[0];
    }
}
