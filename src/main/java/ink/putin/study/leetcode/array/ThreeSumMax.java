package ink.putin.study.leetcode.array;

import java.util.Arrays;

/**
 * 三数之和最大的
 */
public class ThreeSumMax {

    public static void main(String[] args) {
        int[] arr = new int[]{0,2,1,-3};
        System.out.println(threeSumMax(arr));
    }

    public static int threeSumMax(int[] arr) {
        if(arr.length < 3){
            return 0;
        }
        Arrays.sort(arr);
        return arr[arr.length - 1] + arr[arr.length - 2] + arr[arr.length - 3];
    }
}
