package ink.putin.study.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 计算 x^n
 */
public class MyPow {
    static Map<Integer, Double> cache = new HashMap<>();

    public static void main(String[] args) {
        double x = 2;
        int n = 3;
        System.out.println(myPow(x, n));
        System.out.println(myPow(2, 4));
        System.out.println(myPow(2, -1));
        System.out.println(myPow(2, -2));
        System.out.println(myPow(0.00001, 2147483647));
        System.out.println(myPow(1, -2147483648));
        System.out.println(myPow(2, -2147483648));
    }

    public static double myPow(double x, int n) {
        if (cache == null) {
            cache = new HashMap<>(Math.abs(n));
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if(x == 1d){
            return 1;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        double result;
        // n^-5 = 1/n^5
        if (n < 0) {
            if (-n % 2 == 0) {
                result = 1 / (myPow(x, -n / 2) * myPow(x, -n / 2));
            } else {
                result = 1 / (x * myPow(x, -n / 2) * myPow(x, -n / 2));
            }
        } else {
            // 分治思想，n^4 等于 (n*n) * (n*n)
            // 分治思想，n^5 等于 n * (n*n) * (n*n)
            if (n % 2 == 0) {
                result = myPow(x, n / 2) * myPow(x, n / 2);
            } else {
                result = x * myPow(x, n / 2) * myPow(x, n / 2);
            }
        }

        if(result == Double.POSITIVE_INFINITY){
            result =  Double.MAX_VALUE;
        }
        if(result == Double.NEGATIVE_INFINITY){
            result =  Double.MIN_VALUE;
        }
        cache.put(n, result);
        return result;
    }
}
