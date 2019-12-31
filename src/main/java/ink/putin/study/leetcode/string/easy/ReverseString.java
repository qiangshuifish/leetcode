package ink.putin.study.leetcode.string.easy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>反转字符串</p>
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class ReverseString {

    public static String reverseString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = s.toCharArray().length -1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseString(""));
        System.out.println(reverseString(" "));
        System.out.println(reverseString(",./"));
        System.out.println(reverseString("qwe"));
        System.out.println(reverseString("qazwsx"));
    }
}
