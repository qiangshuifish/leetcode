package ink.putin.study.leetcode.string.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * <p></p>
 *
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "holle"
 * 示例 2:
 *
 * 输入: "leetcode"
 * 输出: "leotcede"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class ReverseVowels {


    public String reverseVowels1(String s) {
        StringBuilder sb = new StringBuilder(s);
        Set<Character> vowels = new HashSet();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        //use two pointers approach would be the fastest
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char left = s.charAt(i);
            char right = s.charAt(j);
            while (i < j && !vowels.contains(left)) {
                i++;
                left = s.charAt(i);
            }
            while (i < j && !vowels.contains(right)) {
                j--;
                right = s.charAt(j);
            }
            char temp = left;
            sb.setCharAt(i, right);
            sb.setCharAt(j, temp);
            i++;
            j--;
        }
        return sb.toString();
    }

    public static String reverseVowels(String s){
        if(s == null || "".equals(s)){
            return s;
        }
        char[] chars = s.toCharArray();
        int first = - 1;
        int last = - 1;
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        for (int i = 0, j = chars.length - 1; i < j;) {
            if(isVowels(String.valueOf(chars[i]).toLowerCase())){
                first = i;
            }else{
                // 如果一个位置找到了，需要停下，指导另一个位置也找到
                i++;
            }
            if(isVowels(String.valueOf(chars[j]).toLowerCase())){
                last = j;
            }else{
                j--;
            }
            if(first > -1 && last > -1){
                sb.replace(first,first+1,String.valueOf(chars[last]));
                sb.replace(last,last+1,String.valueOf(chars[first]));
                first = -1;
                last = -1;
                // 置换完毕之后，需要让循环继续走下去
                i++;
                j--;
            }
        }
        return sb.toString();
    }

    public static boolean isVowels(String c){
        return "a".equals(c) || "e".equals(c) | "o".equals(c) || "u".equals(c) | "i".equals(c);
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels(""));
        System.out.println(reverseVowels(" "));
        System.out.println(reverseVowels("a"));
        System.out.println(reverseVowels("1"));
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
        System.out.println(reverseVowels("asdeasd"));
        System.out.println(reverseVowels("Euston saw I was not Sue."));
/*
        StringBuilder sb = new StringBuilder("hello");
        sb.replace(1,2,"o");
        sb.replace(4,5,"e");
        System.out.println(sb)*/;
    }
}
