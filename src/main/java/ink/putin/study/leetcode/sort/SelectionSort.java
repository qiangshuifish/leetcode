package ink.putin.study.leetcode.sort;

/**
 * 选择排序，每次找到最小的值，放在前边
 * @author wpy
 */
public class SelectionSort {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[]{1,8,6,5,4,9,0};
        int min = arr[0];
        int currentMinIndex = 0;

        for (int i = 1; i < arr.length - 1; i++) {
            if(min < arr[i]){
                currentMinIndex = i;
            }
        }
        int temp = arr[currentMinIndex];



    }
}
