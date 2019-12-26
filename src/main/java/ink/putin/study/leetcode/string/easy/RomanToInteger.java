package ink.putin.study.leetcode.string.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class RomanToInteger {
    private static Map<String,Integer> map = new HashMap<>();
    static {
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);

        map.put("L",50);
        map.put("C",100);

        map.put("D",500);
        map.put("M",1000);
    }

    public static int romanToInteger(String str){
        int sum = 0;
        char[] chars = str.toUpperCase().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            Integer current = map.get(String.valueOf(chars[i]));
            Integer next = i+1 < chars.length ? map.get(String.valueOf(chars[i+1])) : 0;

            if(current < next){
                // IV 在 I 时  sum = sum - 1 + 5， 在 V 时 sum = sum + 5，所以这里只需要减去较小数据即可
                sum = sum - current;
            }else{
                sum = sum + current;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(romanToInteger("III"));
        System.out.println(romanToInteger("IV"));
        System.out.println(romanToInteger("IX"));
        System.out.println(romanToInteger("LVIII"));
        System.out.println(romanToInteger("MCMXCIV"));
    }
}
