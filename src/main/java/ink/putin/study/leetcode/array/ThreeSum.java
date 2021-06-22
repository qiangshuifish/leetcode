package ink.putin.study.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 1. 找出三数之和为指定值的所有组合
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,1};
        List<List<Integer>> lists = threeSum(arr);
        System.out.println(lists);
    }


    public static List<List<Integer>> threeSum(int[] arr) {
        int k = 0;
        if(arr.length < 3){
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        // 先排好序，然后再一个一个找
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            int left = i + 1;
            int right = arr.length - 1;
            // sum > k,right-; sum < k,left++;
            while (left < right){
                int sum = a + arr[left] + arr[right];
                if(sum == k){
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    list.add(arr[left]);
                    list.add(arr[right]);
                    if(!result.contains(list)){
                        result.add(list);
                    }

                    right--;
                    left++;
                }else if(sum > k){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return result;
    }
}
