package org.walruscode.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class P1005_sum_after_negations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int n: nums) queue.add(n);

        while (k-- > 0) queue.add(queue.poll() * -1);

        int sum = 0;

        while (!queue.isEmpty()) sum += queue.poll();

        return sum;
    }

    public int largestSumAfterKNegationsV1(int[] nums, int k) {
        int[] numbers = new int[201];
        int sum = 0;
        int maxAbs = 0;
        for (int n: nums) {
            maxAbs = Math.max(maxAbs, Math.abs(n));
            numbers[100 + n]++;
            sum += n;
        }

        if (maxAbs == 0) return 0;

        while (k-- != 0) {
            int i = 100 - maxAbs; // we start always at the very left;
            while (numbers[i] == 0) i++;

            numbers[i]--;
            numbers[200 - i]++;
            sum -= 2 * (i - 100);
        }

        return sum;
    }

    // time O(nlogn), space O(1)
    public int largestSumAfterKNegationsV2(int[] nums, int k) {
        Arrays.sort(nums);

        int prev = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > prev) i--;

            nums[i] *= -1;

            prev = nums[i];

            k--;
            if (k == 0) break;

            if (i + 1 == nums.length) i--;
        }

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        return sum;
    }
}
