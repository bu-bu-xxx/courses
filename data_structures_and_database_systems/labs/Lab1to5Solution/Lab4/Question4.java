package com.test.Lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * COMP5112
 *
 */
public class Question4 {
    /**
     * idea: store all of the pairs and pop k pairs with the minimum sum
     * time complexity: O(max(k, arr1_size * arr2_size) * log(arr1_size * arr2_size))
     * space complexity: O(arr1_size * arr2_size)
     */
    public static List<List<Integer>> kSmallestPairs(int[] arr1, int[] arr2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, (o1, o2) -> {
            return o1[0] + o1[1] - o2[0] - o2[1];
        });

        // add all the number pairs in the heap
        int arr1_size = arr1.length;
        int arr2_size = arr2.length;
        for (int i = 0; i < arr1_size; i++) {
            for (int j = 0; j < arr2_size; j++) {
                minHeap.add(new int[]{arr1[i], arr2[j]});
            }
        }

        // pop k pairs with the minimum sum
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < Math.min(arr1_size * arr2_size, k); i++) {
            int[] tmp_arr = minHeap.poll();
            List<Integer> list = new ArrayList<>();
            list.add(tmp_arr[0]);
            list.add(tmp_arr[1]);
            ans.add(list);
        }
        return ans;
    }

    /**
     * basic idea:
     * select the minimum number pair from all of the possible candidates
     * suppose the indices of top-n number pair is (a1, b1), (a2, b2) ... (an, bn)
     *      then the indices of (n+1)-th number pair must be selected from: (a1+1, b1), (a1, b1 + 1), (a2 + 1, b2), (a2, b2 + 1) ... (an + 1, bn), (an, bn + 1)
     * time complexity: O(min(k, arr1_size) * k)
     * space complexity: O(min(k, arr1_size))
     */
    public static List<List<Integer>> kSmallestPairs2(int[] arr1, int[] arr2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return arr1[o1[0]] + arr2[o1[1]] - arr1[o2[0]] - arr2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int arr1_size = arr1.length;
        int arr2_size = arr2.length;
        // initialize the candidates of the top-1 solution
        for (int i = 0; i < Math.min(arr1_size, k); i++) {
            pq.add(new int[]{i, 0});
        }
        // select k results
        while (k-- > 0 && !pq.isEmpty()) {
            // add the popped (poll) indices to the result, since it is the minimum of all candidates
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(arr1[idxPair[0]]);
            list.add(arr2[idxPair[1]]);
            ans.add(list);
            // add candidates for the next result
            if (idxPair[1] + 1 < arr2_size) {
                pq.add(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    //solve by double pointer
    //...

    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3};
        int k = 3;

        List<List<Integer>> res = kSmallestPairs(arr1, arr2, k);
        for (int i = 0; i < res.size(); i++) {
            System.out.printf("%d %d\n", res.get(i).get(0), res.get(i).get(1));
        }

        System.out.println("----------------------------");

        res = kSmallestPairs2(arr1, arr2, k);
        for (int i = 0; i < res.size(); i++) {
            System.out.printf("%d %d\n", res.get(i).get(0), res.get(i).get(1));
        }
    }
}
