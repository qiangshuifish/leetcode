package ink.putin.study.leetcode.string.easy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
 * 判断第一个字符串ransom能不能由第二个字符串magazines里面的字符构成。
 * 如果可以构成，返回 true ；否则返回 false。
 *
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
 *
 * 注意：
 *
 * 你可以假设两个字符串均只含有小写字母。
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 *
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
public class CanConstruct {

    public static boolean canConstruct(String ransom,String magazine){
        char[] ransomChars = ransom.toCharArray();
        List<String> ransomList = new ArrayList<>(ransomChars.length);
        for (int i = 0; i < ransomChars.length; i++) {
            ransomList.add(String.valueOf(ransomChars[i]).intern());
        }

        char[] magazineChars = magazine.toCharArray();
        List<String> magazineList = new ArrayList<>(magazineChars.length);
        for (int i = 0; i < magazineChars.length; i++) {
            magazineList.add(String.valueOf(magazineChars[i]).intern());
        }

        Map<String, List<String>> ransomMap = ransomList.stream().collect(Collectors.groupingBy(o -> o));
        Map<String, List<String>> magazineMap = magazineList.stream().collect(Collectors.groupingBy(o -> o));

        for (String key : ransomMap.keySet()) {
            if(magazineMap.get(key) == null || ransomMap.get(key).size() > magazineMap.get(key).size()){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("a","b"));
        System.out.println(canConstruct("aa","ab"));
        System.out.println(canConstruct("aa","aab"));
        System.out.println(canConstruct("qwe","aswdgecxczq"));
    }
}
