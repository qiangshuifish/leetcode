package ink.putin.study.leetcode.string.easy;

/**
 * <p>最长公众前缀</p>
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class LongestPrefix {

    public static String longestPrefix(String[] arr){
        String longestPrefix = "";
        if(arr.length <= 0){
            return longestPrefix;
        }
        if(arr.length <= 1){
            return arr[0];
        }
        String firstStr = arr[0];
        for (int i = 0; i < firstStr.toCharArray().length; i++) {
            //暂存一份
            String longestPrefixTemp = longestPrefix;
            // 截取前缀时注意，结束位置需要 i+1 因为 i 会永远小于 firstStr.length()
            // 所以永远截取不到最短字符串的最后一位
            int endIndex = i + 1;
            longestPrefix = firstStr.substring(0, endIndex);
            // 从第一个字符开始
            for (int j = 1; j < arr.length ; j++) {
                // 这里要注意协一下最短字符串可能没前缀长
                if(arr[j].length() < endIndex ||
                        !arr[j].substring(0, endIndex).equals(longestPrefix)){
                    longestPrefix = longestPrefixTemp;
                    break;
                }
            }
        }

        return longestPrefix;
    }

    public static void main(String[] args) {
        System.out.println(longestPrefix(new String[]{""}));
        System.out.println(longestPrefix(new String[]{"a"}));
        System.out.println(longestPrefix(new String[]{"c","c"}));
        System.out.println(longestPrefix(new String[]{"123","12","1"}));
        System.out.println(longestPrefix(new String[]{"a123","12","1"}));
        System.out.println(longestPrefix(new String[]{"a123","a12","a1"}));
        System.out.println(longestPrefix(new String[]{"abcd","abcd","abcdkl"}));
    }
}
