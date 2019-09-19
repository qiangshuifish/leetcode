package ink.putin.study.leetcode.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 呛水滴鱼
 * @version 1.0
 * @apiNote
 * @date 2019-09-18 20:27
 * @since 1.0
 */
public class JavaTest {

    public static void main(String[] args) {
        int i =0,j=0 ,strLength = 5;

        int count = 0;

        while (i < strLength && j < strLength) {
            StringBuilder sb = new StringBuilder();
            sb.append("i:").append(i)
                    .append("j:").append(j)
                    .append("strLength:").append(strLength);
            System.out.println(sb.toString());
            if(count++ > strLength*2){
                break;
            }
        }
    }


    @Test
    public void  test(){
    }


}
