package org.walruscode.leetcode.medium;

public class P74_2d_matrix_search {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 1 && matrix[0].length == 1 && matrix[0][0] == target) return true;
        if (matrix.length == 1 && matrix[0].length == 1 && matrix[0][0] != target) return false;

        int start = 0;
        int end = matrix.length - 1;
        int mid = (start + end) / 2;
        int row = Integer.MAX_VALUE;

        while (start <= end) {
            int current = matrix[mid][0];
            if (current == target) return true;

            if (target < current) {
                if (mid == 0) return false;

                int previousRow = matrix[mid-1][0];
                if (previousRow == target) return true;

                if (target > previousRow) {
                    row = mid-1;
                    break;
                }

                end = mid - 1;
            } else {
                if (mid == matrix.length - 1) {
                    row = mid;
                    break;
                }

                int nextRow = matrix[mid+1][0];
                if (nextRow == target) return true;

                if (target < nextRow) {
                    row = mid;
                    break;
                }

                start = mid + 1;
            }

            mid = (start + end) / 2;
        }

        if (row == Integer.MAX_VALUE) return false;

        start = 0;
        end = matrix[0].length-1;
        mid = (start + end) / 2;

        while (start <= end) {
            int current = matrix[row][mid];

            if (current == target) return true;

            if (target < current) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            mid = (start + end) / 2;
        }

        return false;
    }
}
