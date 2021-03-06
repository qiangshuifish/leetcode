package ink.putin.study.leetcode.string.easy;

/**
 * <p></p>
 *实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class StrStr {

    public static int strStr(String haystack,String needle){
        if(needle == null || "".equals(needle)){
            return 0;
        }
        if(needle.length() > haystack.length()){
            return -1;
        }
        // 这里需要一个等号，因为 needle.length()得到的是needle的字符串数量，不是最后一个字符的index
        for (int i = 0; i <= haystack.length()-needle.length(); i++) {
            if(haystack.substring(i,i+needle.length()).equals(needle)){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("a","a"));
        System.out.println(strStr("12321321",""));
        System.out.println(strStr("12321321","23"));
        System.out.println(strStr("adasdaswqeqweq","wq"));
        System.out.println(strStr("ujmik,yhntgb","nt"));
    }
}
