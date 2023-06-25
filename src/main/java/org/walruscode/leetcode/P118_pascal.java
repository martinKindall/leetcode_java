package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P118_pascal {

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) return List.of();

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> current = new ArrayList<>();
        current.add(1);

        result.add(current);

        for (int i = 2; i <= numRows; i++) {
            List<Integer> newList = new ArrayList<>();
            newList.add(1);

            for (int j = 0; j < current.size() - 1; j++) {
                Integer element = current.get(j) + current.get(j+1);
                newList.add(element);
            }

            newList.add(1);
            result.add(newList);
            current = newList;
        }

        return result;
    }
}
