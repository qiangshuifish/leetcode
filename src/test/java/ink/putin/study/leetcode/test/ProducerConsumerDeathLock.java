package ink.putin.study.leetcode.test;

/**
 * 多生产者消费者线程通讯死锁
 * @author wpy
 *
 */
public class ProducerConsumerDeathLock {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Producer producer0 = new Producer(resource);
        Producer producer1 = new Producer(resource);
        Consumer consumer2 = new Consumer(resource);
        Consumer consumer3 = new Consumer(resource);
        Thread t0 = new Thread(producer0);
        Thread t1 = new Thread(producer1);
        Thread t2 = new Thread(consumer2);
        Thread t3 = new Thread(consumer3);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}
class Producer implements Runnable {
    private Resource resource;
    public Producer(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        while (true) {
            resource.set("资源");
        }
    }
}
class Consumer implements Runnable {
    private Resource resource;
    public Consumer(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        while (true) {
            resource.out();
        }
    }
}
class Resource {
    private String name;
    private int count = 1;
    // 是否生成完毕
    private boolean flag = false;
    public synchronized void set(String name) {
        String threadName = Thread.currentThread().getName();
        while (flag) {
            try {
                System.out.println(threadName+"进入等待状态");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadName+"取得执行权");
        this.name = name + count;
        count++;
        System.out.println("生产者：" + this.name);
        flag = true;
        this.notify();
//        this.notifyAll();
    }
    public synchronized void out() {
        String threadName = Thread.currentThread().getName();
        while (!flag) {
            try {
                System.out.println(threadName+"进入等待状态");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadName+"取得执行权");
        System.out.println("============消费者：" + name);
        flag = false;
        this.notify();
//        this.notifyAll();
    }
}