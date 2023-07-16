package org.walruscode.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P22_generate_parentheses {

    private static class Data {
        final int right, unpaired;
        final String word;

        Data(int right, int unpaired, String word) {
            this.right = right;
            this.unpaired = unpaired;
            this.word = word;
        }
    }

    public List<String> generateParenthesisv2(int n) {
        if (n == 1) return List.of("()");

        List<String> res = new ArrayList<>();

        Stack<Data> myStack = new Stack<>();
        myStack.push(new Data(0, 0, ""));

        while (!myStack.isEmpty()) {
            Data current = myStack.pop();

            if (current.word.length() == 2*n) {
                res.add(current.word);
                continue;
            }

            if (current.right < n) {
                myStack.push(new Data(current.right + 1, current.unpaired + 1, current.word + "("));
            }

            if (current.unpaired == 0) {
                continue;
            }

            myStack.push(new Data(current.right, current.unpaired - 1, current.word + ")"));
        }

        return res;
    }

    private List<String> results;

    public List<String> generateParenthesis(int n) {
        if (n == 1) return List.of("()");

        results = new ArrayList<>();

        backtrack(n, 0, 0, "");

        return results;
    }

    void backtrack(int n, int right, int unpaired, String current) {
        if (current.length() == 2*n) {
            results.add(current);
            return;
        }

        if (right < n) {
            backtrack(n, right + 1, unpaired + 1, current + "(");
        }

        if (unpaired == 0) {
            return;
        }

        backtrack(n, right, unpaired - 1, current + ")");
    }
}
