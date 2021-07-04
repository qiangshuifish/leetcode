package ink.putin.study.leetcode.string.medium;

import java.util.ArrayList;
import java.util.List;

/**
 *给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 回文串（palindromic string）是指这个字符串无论从左读还是从右读，所读的顺序是一样的；简而言之，回文串是左右对称的。所谓最长回文子串问题，是指对于一个给定的母串
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String str = "cbsadsadbd";

        // 解题思路
//        1.首先需要顺序的记录字符，这样才能正确的获取字符串，所以这次不能用Set
        System.out.println(longestPalindrome(str));
        System.out.println(longestPalindrome(""));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("aa"));

    }

    private static String longestPalindrome(String str) {
        boolean[][] dp = new boolean[str.length()][str.length()];
        String maxStr = null;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(str.charAt(i) == str.charAt(j) && (i -j <=2 || str.charAt(i-1) == str.charAt(j + 1) )){
                    String substring = str.substring(j, i + 1);
                    maxStr = substring.length() > maxStr.length() ? substring : maxStr;
                }
            }
        }
        return maxStr;
    }
}
