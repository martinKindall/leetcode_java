package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P412_fizz_buzz {
    public List<String> fizzBuzz(int n) {
        var answer = new ArrayList<String>();

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                answer.add("FizzBuzz");
            } else if (i % 3 == 0) {
                answer.add("Fizz");
            } else if (i % 5 == 0) {
                answer.add("Buzz");
            } else {
                answer.add(String.valueOf(i));
            }
        }

        return answer;
    }
}
