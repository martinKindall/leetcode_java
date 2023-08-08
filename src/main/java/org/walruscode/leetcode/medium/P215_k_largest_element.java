package org.walruscode.leetcode.medium;

public class P215_k_largest_element {

    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) return nums[0];

        int[] minHeap = new int[k];

        for (int i = 0; i < k; i++) {
            minHeap[i] = nums[i];
        }

        buildMinHeap(minHeap);

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap[0]) {
                minHeap[0] = nums[i];
                minHeapify(minHeap, 0);
            }
        }

        return minHeap[0];
    }

    private void buildMinHeap(int[] nums) {

        for (int i = nums.length >> 1; i >= 0; i--)
            minHeapify(nums, i);
    }

    private void minHeapify(int[] nums, int i) {
        int l = left(i);
        int r = right(i);

        int size = nums.length;

        int smallest = l < size && nums[l] < nums[i] ? l : i;

        if (r < size && nums[r] < nums[smallest])
            smallest = r;

        if (smallest != i) {
            int temp = nums[i];
            nums[i] = nums[smallest];
            nums[smallest] = temp;

            minHeapify(nums, smallest);
        }
    }

    public int findKthLargestSlower(int[] nums, int k) {
        if (nums.length == 1) return nums[0];

        buildMaxHeap(nums);

        int size = nums.length;

        for (int i = nums.length - 1; i >= nums.length - k; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            size--;

            maxHeapify(nums, size, 0);
        }

        return nums[nums.length - 1 - (k - 1)];
    }

    private void buildMaxHeap(int[] nums) {

        for (int i = nums.length >> 1; i >= 0; i--)
            maxHeapify(nums, nums.length, i);
    }

    private void maxHeapify(int[] nums, int size, int i) {
        int l = left(i);
        int r = right(i);

        int largest = l < size && nums[l] > nums[i] ? l : i;

        if (r < size && nums[r] > nums[largest])
            largest = r;

        if (largest != i) {
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;

            maxHeapify(nums, size, largest);
        }
    }

    private int left(int i) {
        return i << 1;
    }

    private int right(int i) {
        return (i << 1) + 1;
    }
}
