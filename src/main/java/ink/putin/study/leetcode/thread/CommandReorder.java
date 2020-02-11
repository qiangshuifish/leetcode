package ink.putin.study.leetcode.thread;

/**
 * @author 呛水滴鱼
 * @version 1.0
 * @apiNote 指令重排
 * @date 2020-02-11 14:43
 * @since 1.0
 */
public class CommandReorder {

    private static int a,b,x,y;

    public static void main(String[] args) throws InterruptedException {

        int count = 0;
        while (true){

            a = 0;
            b = 0;
            x = 0;
            y = 0;

            count++;
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });


            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println("第" + count + "次,   x = "+x+", y = "+y);

            if(x == 0 &&  y == 0){
                break;
            }

        }

    }
}
