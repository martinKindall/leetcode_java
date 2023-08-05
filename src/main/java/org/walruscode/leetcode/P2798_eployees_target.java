package org.walruscode.leetcode;

import java.util.Arrays;
import java.util.List;

public class P2798_eployees_target {

    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        return (int) Arrays.stream(hours).filter(h -> h >= target).count();
    }

    public int numberOfEmployeesWhoMetTargetV2(int[] hours, int target) {

        int employees = 0;

        for (int hour: hours) {
            if (hour >= target) employees++;
        }

        return employees;
    }
}
