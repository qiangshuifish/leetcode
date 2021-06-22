package ink.putin.study.leetcode.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int left = low;
        int right = high;
        //temp就是基准位
        int pvt = arr[left];

        while (left < right) {
            //先看右边，依次往左递减
            while (pvt <= arr[right] && left < right) {
                right--;
            }
            //再看左边，依次往右递增
            while (pvt >= arr[left] && left < right) {
                left++;
            }
            //如果满足条件则交换
            if (left < right) {
                int t = arr[right];
                arr[right] = arr[left];
                arr[left] = t;
            }

        }
        System.out.println("before == " + Arrays.toString(arr) + " low:" + low + " left:" + left + " right:" + right + " pvt：" + pvt);
        //最后将基准数字，放到left和right相等的位置
        //
        arr[low] = arr[left];
        arr[left] = pvt;
        System.out.println("after == " + Arrays.toString(arr));
        //递归调用左半数组
        quickSort(arr, low, left - 1);
        //递归调用右半数组
        quickSort(arr, left + 1, high);
    }


    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 1, 4};
        System.out.println(Arrays.toString(arr));
        System.out.println();
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
