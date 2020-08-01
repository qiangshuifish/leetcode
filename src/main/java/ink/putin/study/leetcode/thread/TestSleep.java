package ink.putin.study.leetcode.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 呛水滴鱼
 * @version 1.0
 * @apiNote
 * @date 2020-03-22 21:32
 * @since 1.0
 */
public class TestSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            System.out.println("a  线程执行中");
            int i = 0;
            while (i < 1000){
                System.out.println(i++);
            }
        });
        a.start();

        a.sleep(1000);
        System.out.println("Thread"+Thread.currentThread().getName()+":"+Thread.currentThread().getState());
        System.out.println("Thread"+a.getName()+":"+a.getState());
    }
}
