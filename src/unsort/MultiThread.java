package unsort;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: renjie
 * @description: 两个线程交替打印奇偶数0-100
 * @date: 2022/7/19 9:58
 */
public class MultiThread {

    private volatile int count = 0;
    Lock lock = new ReentrantLock();
    Condition doubleCondition = lock.newCondition();
    Condition oddCondition = lock.newCondition();

    public class ThreadA implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (count <= 100) {
                    if (count % 2 == 0) {
                        System.out.println("偶数" + count++);
                    }
                    oddCondition.signal();
                    doubleCondition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public class ThreadB implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (count <= 100) {
                    if (count % 2 != 0) {
                        System.out.println("奇数" + count++);
                    }
                    doubleCondition.signal();
                    oddCondition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void print() {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }

    public static void main(String[] args) {

        MultiThread multiThread = new MultiThread();
        multiThread.print();
    }
}
