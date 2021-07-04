package ink.putin.study.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[]{1}));
        System.out.println(maxSubArray(new int[]{-1}));
        System.out.println(maxSubArray(new int[]{-1,2,1,3}));
    }

    public static int maxSubArray01(int[] nums) {
        // 记录最大值
        int max = nums[0];
        // 记录上一个的最大值 f(n-1)
        int pre = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            // f(n) = Math.max(a[n],a[n]+f(n-1))
            pre = Math.max(nums[i],pre + nums[i]);
            // 取最大值
            max = Math.max(max,pre);
        }
        return pre;
    }


    /**
     * f(n) = Math.max(a[n],a[n] + f(n - 1))
     */
    public static int maxSubArray(int[] nums) {
        cache = new HashMap<>((int)(nums.length / 0.5) + 1);
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            max = Math.max(max,maxSubArray(nums,i));
        }
        return max;
    }

    static Map<Integer,Integer> cache = null;
    public static int maxSubArray(int[] nums, int n) {
        if (n == 0) return nums[0];
        return Math.max(nums[n],nums[n] + maxSubArray(nums,n-1));
    }
}
