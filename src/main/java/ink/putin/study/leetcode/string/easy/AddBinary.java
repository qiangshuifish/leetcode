package ink.putin.study.leetcode.string.easy;

import javax.print.attribute.IntegerSyntax;

/**
 * <p></p>
 *
 *给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class AddBinary {

    public static String addBinary(String str1,String str2){
        int i = Integer.parseInt(str1, 2);
        int j = Integer.parseInt(str2, 2);
        return Integer.toBinaryString(i + j);
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11","1"));
        System.out.println(addBinary("1010","1011"));
    }
}
