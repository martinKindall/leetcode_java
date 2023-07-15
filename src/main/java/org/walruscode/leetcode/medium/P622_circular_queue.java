package org.walruscode.leetcode.medium;

public class P622_circular_queue {

    class MyCircularQueue {

        private int[] circular;
        int front;
        int rear;
        int maxSize;
        int items;

        public MyCircularQueue(int k) {
            maxSize = k;
            circular = new int[k];
            front = 0;
            rear = 1;
            items = 0;
        }

        public boolean enQueue(int value) {
            if (items == maxSize) return false;

            rear = Math.floorMod(rear - 1, maxSize);
            circular[rear] = value;
            items++;

            return true;
        }

        public boolean deQueue() {
            if (items == 0) return false;

            rear = (rear + 1) % maxSize;

            items--;

            return true;
        }

        public int Front() {
            if (items == 0) return -1;

            return circular[front];
        }

        public int Rear() {
            if (items == 0) return -1;

            return circular[rear];
        }

        public boolean isEmpty() {
            return items == 0;
        }

        public boolean isFull() {
            return items == maxSize;
        }
    }
}
