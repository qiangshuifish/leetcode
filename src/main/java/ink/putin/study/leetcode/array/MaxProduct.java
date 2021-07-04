package ink.putin.study.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProduct {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct(new int[]{-2, 0, -1}));
        System.out.println(maxProduct(new int[]{-3, -1, -1}));
    }


    static Map<Integer, Integer> maxMap = null;
    static Map<Integer, Integer> minMap = null;

    /**
     * 连续的子数组最大乘积
     * if a[n] >= 0
     * f(n) = Math.max(a[n] * Fmax(n),a[n])  Fmax = f(n - 1)
     * else a[n] < 0
     * f(n) = Math.min(a[n] * Fmin(n),a[n])
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        minMap = new HashMap<>((int) (nums.length / 0.75) + 1);
        maxMap = new HashMap<>((int) (nums.length / 0.75) + 1);
        minMap.put(0, nums[0]);
        maxMap.put(0, nums[0]);

        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, maxProduct(nums, i));
        }
        return max;
    }

    public static int maxProduct(int[] nums, int n) {
        if (n == 0) {
            return nums[0];
        }
        if(nums[n] >= 0){
            int max = Math.max(nums[n], maxMap.get(n - 1) * nums[n]);
            int min = Math.min(nums[n], minMap.get(n - 1) * nums[n]);
            maxMap.put(n, Math.max(max, min));
            minMap.put(n, Math.min(max, min));
            return max;
        }else{
            int max = Math.max(nums[n], minMap.get(n - 1) * nums[n]);
            int min = Math.min(nums[n], maxMap.get(n - 1) * nums[n]);
            maxMap.put(n, Math.max(max, min));
            minMap.put(n, Math.min(max, min));
            return max;
        }
    }

    public static int maxProduct01(int[] nums) {
        // 上一位的最大值
        int maxF = nums[0];
        // 上一位的最小值
        int minF = nums[0];

        // 结果最大值
        int maxValue = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF;
            int mn = minF;
            // 取  上一个的大值*nums[i] 和 上一个最小值的
            if(nums[i] >= 0){
                maxF = Math.max(nums[i],nums[i] * mx);
                minF = Math.min(nums[i],nums[i] * mn);
            }else{
                maxF = Math.max(nums[i],nums[i] * mn);
                minF = Math.min(nums[i],nums[i] * mx);
            }
            maxValue = Math.max(maxF, maxValue);
        }
        return maxValue;
    }
}
