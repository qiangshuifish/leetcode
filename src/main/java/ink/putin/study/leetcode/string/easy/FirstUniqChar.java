package ink.putin.study.leetcode.string.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class FirstUniqChar {
    /**
     * 使用map 存储字符和支付对应的个数，再通过字符找其在字符串的索引位置，并比较
     * 此处可以使用一次存储的方式，一次性存储，字符，个数，以及索引位置
     * @param s
     * @return
     */
    public static int firstUniqChar(String s){
        if(s==null || s.length() == 0){
            return -1;
        }
        Map<Character,Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if(map.get(c) != null){
                map.put(c,map.get(c) + 1);
            }else{
                map.put(c,1);
            }
        }

        int index = -1;
        int count = 0;
        for (Character c : map.keySet()) {
            if(map.get(c) != null && map.get(c).equals(1)){
                if(index == -1){
                    index = s.indexOf(c);
                }else if(index > s.indexOf(c)){
                    index = s.indexOf(c);
                }
            }else{
                count++;
            }
        }

        return count==s.length()?-1:index;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("") );
        System.out.println(firstUniqChar(" ") );
        System.out.println(firstUniqChar("leetcode") );
        System.out.println(firstUniqChar("loveleetcode") );
        System.out.println(firstUniqChar("qwertyu123") );
        System.out.println(firstUniqChar("qwert2qw") );
    }
}
