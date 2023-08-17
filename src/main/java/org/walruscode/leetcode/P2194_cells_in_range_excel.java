package org.walruscode.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P2194_cells_in_range_excel {

    public List<String> cellsInRange(String s) {
        List<String> result = new ArrayList<>();

        char startCol = s.charAt(0);
        char endCol = s.charAt(3);

        char startRow = s.charAt(1);
        char endRow = s.charAt(4);

        while (startCol <= endCol) {

            startRow = s.charAt(1);
            while (startRow <= endRow) {
                result.add(startCol + "" + startRow);

                startRow++;
            }

            startCol++;
        }

        return result;
    }
}
