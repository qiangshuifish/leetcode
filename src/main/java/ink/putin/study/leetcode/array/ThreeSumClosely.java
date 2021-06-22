package ink.putin.study.leetcode.array;


import java.util.Arrays;

/**
 * 三数之和最接近的 k 的一组
 */
public class ThreeSumClosely {

    public static void main(String[] args) {
        int[] arr = new int[]{0,2,1,-3};
        int k = 1;
        System.out.println(threeSumClosely(arr,k));
    }

    public static int threeSumClosely(int[] arr,int k) {
        if(arr.length < 3){
            return 0;
        }

        int miniSum = arr[0] + arr[1] + arr[2];
        int miniSub = Math.abs(miniSum - k);
        // 先排好序，然后再一个一个找
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right){
                // 当前值 + 小数 + 大数
                // 如果 sum > k,大数左移； sum < k,小数左移，
                int sum = a + arr[left] + arr[right];
                if(sum == k){
                    return sum;
                }
                int sub = Math.abs(sum - k);
                if(sub < miniSub) {
                    miniSum = sum;
                    miniSub = sub;
                }
                if(sum > k){
                    right--;
                }else{
                    left++;
                }

            }
        }
        return miniSum;
    }
}
