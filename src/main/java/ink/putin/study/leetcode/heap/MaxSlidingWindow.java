package ink.putin.study.leetcode.heap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

 返回滑动窗口中的最大值。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 输出：[3,3,5,5,6,7]
 解释：
 滑动窗口的位置                最大值
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 */
public class MaxSlidingWindow {


    public static void main(String[] args) {

        int k = 3;
        int[] arr = new int[]{1,3,1,2,0,5};

        if(arr.length <= 1){
            return;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> -(o1 - o2));
        int[] result = new int[arr.length - k + 1];

        int left = 0;
        int right = left + k - 1;
        int count = 0;
        while (right < arr.length){
            if(queue.size() == 0){
                for (int i = left; i <= right ; i++) {
                    queue.add(arr[i]);
                }
            }else{
                queue.add(arr[right]);
            }

            result[count] = queue.peek();
            count++;

            queue.remove(arr[left]);
            left++;
            right++;
        }

        System.out.println(Arrays.toString(result));
    }
}
