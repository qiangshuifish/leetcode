package ink.putin.study.leetcode.string;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class LengthOfLongestSubstring {


    private static int lengthOfLongestSubstring(String str) {
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length() + 1; j++) {
                String substring = str.substring(i, j);
                if (!isRepetitionStr(substring)) {
                    max = Math.max(max, substring.length());
                }
            }
        }
        return max;
    }

    private static boolean isRepetitionStr(String str) {
        Set<Character> charSequences = new HashSet<>(str.length());
        for (char aChar : str.toCharArray()) {
            charSequences.add(aChar);
        }
        return charSequences.size() != str.length();
    }

    public static void main(String[] args) {
        // 解题思路 这里用最容易理解的方式，（时间复杂度高）
        // 1. 两个循环递归出字符串所有的子字符串
        // 2. 判断每个子字符串是不是重复的字符串
        // 3. 记录非重复字符串的最大长度
        String str = "1234a3jmjjbggu";

        int i = lengthOfLongestSubstring(str);
        System.out.println(i);

        // 滑动法
        int i1 = lengthOfLongestSubstring_01(str);
        System.out.println(i1);
    }

    /**
     * 滑动法 思路
     * 1.使用一个Set来存储字符，把字符从头到尾遍历一遍,
     * 2.把所以第一次出现的字符都放入set里头
     * 3.直到出现第一个重复字符时，删掉第一个字符,就循环当前子字符串，删除该重复字符及其以前的字符
     *   比如：1234a3jmjjbggu，1234a再次遇到3时，就把123删了，留下4a,然后再把a放进去，继续前边的操作。
     * 4.再经行第3步操作时，会记录下来当前出现过的最长的字符串的长度，然后每添加一个字符都能set长度和存储的最大长度对比
     * @param str
     * @return
     */
    public static int lengthOfLongestSubstring_01(String str) {
        int strLength = str.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;

        int count = 0;
        while (i < strLength && j < strLength) {
            count++;
            log.info("count:{},i:{},j:{},set:{},ans:{}",count,i,j,set,ans);
            // try to extend the range [i, j]
            if (!set.contains(str.charAt(j))){
                set.add(str.charAt(j++));
                ans = Math.max(ans, j - i);
            }else {
                set.remove(str.charAt(i++));
            }
        }
        return ans;
    }

}
