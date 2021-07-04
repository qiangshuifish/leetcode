package ink.putin.study.leetcode.array;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumTotal {

    public static void main(String[] args) {
        List<List<Integer>> list = Lists.newArrayList();
        list.add(Lists.newArrayList(2));
        list.add(Lists.newArrayList(3, 4));
        list.add(Lists.newArrayList(6, 5, 7));
        list.add(Lists.newArrayList(4, 1, 8, 3));
        System.out.println(minimumTotal(list));

        cache.clear();
        list.clear();
        list.add(Lists.newArrayList(-10));

        cache.clear();
        list.clear();
        list.add(  Lists.newArrayList(-1));
        list.add(  Lists.newArrayList(2,3));
        list.add(Lists.newArrayList(1,-1,-3));
        System.out.println(minimumTotal(list));
    }

    /**
     * f(n) = n + Match.max(f(n),f(n+1))
     *
     * @param triangle
     * @return
     */
    static Map<String,Integer> cache = null;
    public static int minimumTotal(List<List<Integer>> triangle) {
        return minimumTotal(triangle, 0, 0);
    }

    /**
     * f(n) = n + Next(Match.min(f(n),f(n+1)))
     * 如果 row 是最后一行，放回 Math.min(index,index+1),如果index是最后一个元素，不能+1
     */
    public static int minimumTotal(List<List<Integer>> triangle, int row, int index) {
        if(cache == null){
            cache = new HashMap<>();
        }
        final String key = row + "_" + index;
        if(cache.get(key) != null){
            return cache.get(key);
        }
        // 到最后一行的时候，只需要取当前值
        if (row == triangle.size() - 1) {
            final List<Integer> integers = triangle.get(row);
            return integers.get(index);
        }
        final int min = Math.min(minimumTotal(triangle, row + 1, index), minimumTotal(triangle, row + 1, index + 1));
        final int value = triangle.get(row).get(index) + min;
        cache.put(key,value);
        return value;
    }
}
