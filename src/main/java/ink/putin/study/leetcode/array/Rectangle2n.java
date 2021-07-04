package ink.putin.study.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 我们可以用2x1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2x1的小矩形无重叠地覆盖一个2xn的大矩形，总共有多少种方法？
 *
 * 1. 当 n=1,r=1; n=2,r=2; n=3,r=3; n=4,r=5;
 * 2. 看结果像斐波那契数列
 *
 * 因为是2*n,所以可解的思路以竖着放的个数来定
 * 分析：
 * 1. 当n=2时，r=2; 竖着放0个，横着放2个；  竖着放1个无解，           竖着放2个，所以两种放法;
 * 2. 当n=3时，r=3; 竖着放0个无解，        竖着放1个无解，横着放2（两种放法），   竖着放2个，无解，           竖着放3个
 * 3. 当n=4时，r=5; 竖着放0个，横着4个；竖着放1个无解,  竖着放2，横着放2(3种放法)，  竖着放3无解   竖着放4
 *
 *
 * 1. n为奇数时，竖着放奇数个，n为偶数时，竖着放偶数个
 * 2. 当n=2和n=4的异同
 *  a. 竖着放0个可以，竖着放1个无解，竖着放n个可以  所以 f(n) = f(n-2) + fx
 *  b. 当n=4时，横着放的两个，可以认为是一个，因为他们不能分开 所以 fx = f(n-1)
 *
 *
 *  推导出公式 f(n) = f(n-1) + f(n - 2) 斐波那契数列f
 */
public class Rectangle2n {

    public static void main(String[] args) {
        System.out.println(Fib.fib(0));
        System.out.println(Fib.fib(1));
        System.out.println(Fib.fib(2));
        System.out.println(Fib.fib(3));
        System.out.println(Fib.fib(4));
        System.out.println(Fib.fib(5));
    }
}
