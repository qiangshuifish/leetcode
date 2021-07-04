package ink.putin.study.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Fib {
    public static void main(String[] args) {
        System.out.println(fib(0));
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
        System.out.println(fib(5));
        System.out.println(fib(6));
    }

    static Map<Integer, Integer> cache = null;

    public static int fib(int n) {
        if (cache == null) {
            cache = new HashMap<>((int) (n * 0.75) + 1);
        }
        if (cache.get(n) != null) {
            return cache.get(n);
        }

        int result = 0;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        result = fib(n - 1) + fib(n - 2);
        cache.put(n, result);
        return result;
    }
}
