package org.walruscode.leetcode;

import java.util.Stack;

public class P682_baseball_game {

    public int calPoints(String[] operations) {
        Stack<Integer> records = new Stack<>();

        for (String op: operations) {
            if (op.equals("+")) {
                Integer prev1 = records.pop();
                Integer prev2 = records.pop();

                records.push(prev2);
                records.push(prev1);
                records.push(Integer.sum(prev1, prev2));
            } else if (op.equals("C")) {
                records.pop();
            } else if (op.equals("D")) {
                Integer prev = records.pop();

                records.push(prev);
                records.push(prev*2);
            } else {
                records.push(Integer.parseInt(op));
            }
        }

        int sum = 0;

        while (!records.isEmpty()) {
            sum += records.pop();
        }

        return sum;
    }
}
