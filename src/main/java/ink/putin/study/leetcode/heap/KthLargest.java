package ink.putin.study.leetcode.heap;

import java.util.PriorityQueue;

/**
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 */
public class KthLargest {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(3);
        int k = 3;
        int[] arr = new int[]{};

        for (int i = 0; i < arr.length; i++) {
            add(priorityQueue, k, arr, i);
        }
        System.out.println(priorityQueue.peek());

        //[[1,[]],[-3],[-2],[-4],[0],[4]]
        int[] arr2 = new int[]{-3,-2,-4,0,4};
        for (int i = 0; i < arr2.length; i++) {
            add(priorityQueue,k,arr2,i);
            System.out.println(priorityQueue.peek());
        }
    }

    private static void add(PriorityQueue<Integer> priorityQueue, int k, int[] arr, int i) {
        if(priorityQueue.size() < k){
            priorityQueue.add(arr[i]);
        }else{
            if(priorityQueue.peek() < arr[i]){
                priorityQueue.poll();
                priorityQueue.add(arr[i]);
            }
        }
    }
}
