package org.walruscode.leetcode.medium;

public class P1472_browser_history {
    class BrowserHistory {

        String[] history;
        int size;
        int current;

        public BrowserHistory(String homepage) {
            history = new String[5000];
            size = 1;
            current = 0;
            history[current] = homepage;
        }

        public void visit(String url) {
            history[++current] = url;
            size = current + 1;
        }

        public String back(int steps) {
            current = Math.max(0, current - steps);
            return history[current];
        }

        public String forward(int steps) {
            current = Math.min(size - 1, current + steps);
            return history[current];
        }
    }
}
