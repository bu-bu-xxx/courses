package com.test.Lab4;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * COMP5112
 *
 */
public class Question2 {
    public static int findKthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<>() {
            public int compare(Integer a, Integer b) {
                //a - b is min heap
                //b - a is max heap
                return b - a;
            }
        });

        int n_ele = arr.length;
        for (int i = 0; i < Math.min(k, n_ele); i++) {
            heap.add(arr[i]);
        }
        for (int i = k; i < n_ele; i++) {
            int tmp_max = heap.peek();
            if (tmp_max > arr[i]) {
                heap.poll();
                heap.add(arr[i]);
            }
        }

        return heap.peek();
    }

    public static void main(String[] arg) {
        int[] arr = {51, 22, 73, 82, 14, 67, 38, 19, 98, 25};
        int k = 4;
        int res = findKthSmallest(arr, k);
        System.out.println(res);


    }
}
