package ink.putin.study.leetcode.array;

import java.util.Random;

public class Top100 {
    public static int[] getTop100(int[] inputArray) {

        // 第一步得到数据中最大的数字
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < inputArray.length; i++) {
            if (maxValue < inputArray[i]) {
                maxValue = inputArray[i];
            }
        }

        // 构建一个字节数组，用户存放数组的数字，该位为1表示该数
        byte[] bitmap = new byte[maxValue+1];
        for (int i = 0; i < inputArray.length; i++) {
            int value=inputArray[i];
            bitmap[value] = 1;
        }

        // 遍历数组，取数组最后100位为1的
        int[] result = new int[100];
        int index = 0;
        for (int i = maxValue; i >= 0 & index < 100; i--) {
            if (bitmap[i] == 1) {
                result[index++] = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int numberCount = 100000000;
        int maxNumber = numberCount;
        int inputArray[] = new int[numberCount];
        Random random = new Random();
        for (int i = 0; i < numberCount; i++) {
            inputArray[i] = Math.abs(random.nextInt(maxNumber));
        }
        System.out.println("Sort begin...");
        long current = System.currentTimeMillis();
        int[] result = Top100.getTop100(inputArray);
        System.out.println(System.currentTimeMillis() - current);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ",");
        }
    }
}