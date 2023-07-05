package org.walruscode.leetcode;

public class P1122_relative_sort {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] numbers = new int[1001];

        for (int num: arr1) numbers[num]++;

        int k = 0;
        for (int j: arr2) {
            while (numbers[j] > 0) {
                arr1[k] = j;
                k++;
                numbers[j]--;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            while (numbers[i] > 0) {
                arr1[k] = i;
                k++;
                numbers[i]--;
            }
        }

        return arr1;
    }
}
