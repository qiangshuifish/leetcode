package ink.putin.study.leetcode.string.easy;

/**
 * <p></p>
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class AddStrings {

    /**
     * 使用从各位相加的原理，挨个加，满10进1
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1,String num2){

        int maxLength = Math.max(num1.length(), num2.length());
        int temp = 0;
        StringBuilder result = new StringBuilder(maxLength);
        for (int i = 1; i <= maxLength; i++) {
            char num1LastChar;
            if(num1.length() >= i){
                num1LastChar = num1.charAt(num1.length() - i);
            }else{
                num1LastChar = '0';
            }

            char num2LastChar;
            if(num2.length() >= i){
                num2LastChar = num2.charAt(num2.length() - i);
            }else{
                num2LastChar = '0';
            }
            // 先装字符串，不然不会是数字相加
            int sum = Integer.valueOf(String.valueOf(num1LastChar)) + Integer.valueOf(String.valueOf(num2LastChar)) + temp;
            // 这里需要等于，因为等于10进一位
            if(sum >= 10){
                temp =  sum /10;
                result.append(sum % 10);
            }else{
                result.append(sum);
                temp = 0;
            }
        }
        if(temp != 0){
            result.append(temp);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addStrings("1","9"));
        System.out.println(addStrings("19","9"));
        System.out.println(addStrings("9","19"));
        System.out.println(addStrings("9","9"));
        System.out.println(addStrings("12","21"));
        System.out.println(addStrings("1","2"));
        System.out.println(addStrings("3","4"));
        System.out.println(addStrings("11111111111111111111","11111111111111111111"));
    }
}
