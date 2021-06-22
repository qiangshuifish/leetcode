package ink.putin.study.leetcode.array;

import java.util.Arrays;

/**
 * 三数之和的最大乘积
 */
public class ThreeMaximumProduct {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,-20,-10,1,2,3,4};
        Arrays.sort(nums);
        int length = nums.length;
        System.out.println(Math.max(nums[length - 1]*nums[length - 2]*nums[length - 3],nums[0]*nums[1]*nums[length - 1]));
    }
}
