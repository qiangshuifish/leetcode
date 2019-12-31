package ink.putin.study.leetcode.string.easy;

/**
 * <p>验证回文字符串</p>
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class IsPalindrome {

    /**
     * 字符串朝两个方向走，每次都比较两个字符（非字母数字取下一个）
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(chars[i])) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(chars[j])) {
                j--;
            }
            if (Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j])) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

/*    public static boolean isPalindrome(String str){

        String s = str.replaceAll("\\^[\\w+]*", "");
        System.out.println(s);
        return false;
    }*/

    public static void main(String[] args) {
        System.out.println(isPalindrome("aa"));
        System.out.println(isPalindrome("aba"));
        System.out.println(isPalindrome("ab,a"));
        System.out.println(isPalindrome("ab,a"));
        System.out.println(isPalindrome("a,bb a"));
    }
}
