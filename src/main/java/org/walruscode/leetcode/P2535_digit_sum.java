package org.walruscode.leetcode;

public class P2535_digit_sum {
    public int differenceOfSum(int[] nums) {

        int elementSum = 0;
        int digitSum = 0;

        for (int num : nums) {
            elementSum += num;
            digitSum += digitSum(num);
        }

        return Math.abs(elementSum - digitSum);
    }

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    private int digitSumV2(int num) {
        String value = String.valueOf(num);

        int sum = 0;
        for (int i = 0; i < value.length(); i++) {
            sum += Integer.parseInt(String.valueOf(value.charAt(i)));
        }

        return sum;
    }
}
