package ink.putin.study.leetcode.string.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>有效括号</p>
 *给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class ValidBracket {

    public static boolean isValid(String s) {
        // 这个使用堆栈最合适，遇到 ([{,往堆栈里头push，遇到)]}就pop,后进先出策略
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(c == '(' || c == '[' || c== '{'){
                stack.push(c);
            }else {
                // 说明第一个就不是([{
                if(stack.isEmpty()){
                    return false;
                }
                // 遇到 )]} 返回队列最后一个，看是否匹配
                if(c == ')' || c == ']' || c== '}'){
                    if(getRight(stack.peek()) != c){
                        return false;
                    }else{
                        stack.pop();
                    }
                }
            }
        }

        return stack.isEmpty();
    }

    private static char getRight(char c){
        switch (c){
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(isValid(""));
        System.out.println(isValid("("));
        System.out.println(isValid("()"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("(()"));
        System.out.println(isValid("(())"));
        System.out.println(isValid("([])"));
        System.out.println(isValid("({})"));
        System.out.println(isValid("()[]{}"));
    }
}
