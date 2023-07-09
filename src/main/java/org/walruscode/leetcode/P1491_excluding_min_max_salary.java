package org.walruscode.leetcode;

public class P1491_excluding_min_max_salary {

    public double average(int[] salary) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        double average = 0.0;

        for (int i = 0; i < salary.length; i++) {
            if (i == 2) {
                if (min > max) {
                    int temp = min;
                    min = max;
                    max = temp;
                }
            }
            else if (i == 1) {
                min = salary[i];
                continue;
            }
            else if (i == 0) {
                max = salary[i];
                continue;
            }

            if (min > salary[i]) {
                average *= (i - 2);
                average += min;
                average /= (i - 1);
                min = salary[i];
            } else if (max < salary[i]) {
                average *= (i - 2);
                average += max;
                average /= (i - 1);
                max = salary[i];
            } else {
                average *= (i - 2);
                average += salary[i];
                average /= (i - 1);
            }
        }

        return average;
    }
}
