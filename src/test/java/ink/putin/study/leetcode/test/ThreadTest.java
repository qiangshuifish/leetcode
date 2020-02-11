package ink.putin.study.leetcode.test;

/**
 * @author 呛水滴鱼
 * @version 1.0
 * @apiNote
 * @date 2019-12-26 21:52
 * @since 1.0
 */
public class ThreadTest {
    static class SynchronizedObject{
        public synchronized void print(){
            if(Thread.currentThread().getName().equals("a")){
                System.out.println("a 线程 suspend ");
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final SynchronizedObject object = new SynchronizedObject();
        Thread a = new Thread("a"){
            @Override
            public void run() {
                System.out.println("a 线程执行了");
                object.print();
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a 结束");
            }
        };
        a.setDaemon(true);
        a.start();

        Thread b = new Thread("b"){
            @Override
            public void run() {
                System.out.println("b 线程执行了");
                object.print();
                System.out.println("b 结束");
            }
        };
        b.start();
    }
}
