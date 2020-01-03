package ink.putin.study.leetcode.string.easy;

import java.util.stream.Stream;

/**
 * <p></p>
 *
 *统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * 示例:
 *
 * 输入: "Hello, my name is John"
 * 输出: 5
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class CountSegments {

    public static int countSegments(String s){
        String[] split = s.trim().split(" ");
        return Long.valueOf(Stream.of(split).filter(s1 ->  s1 != null && !s1.trim().equals("")).count()).intValue();
    }

    public static void main(String[] args) {
        System.out.println(countSegments(""));
        System.out.println(countSegments(" "));
        System.out.println(countSegments("1  2"));
        System.out.println(countSegments("1 2 "));
        System.out.println(countSegments("1 2 d  sad, das  "));
    }
}
