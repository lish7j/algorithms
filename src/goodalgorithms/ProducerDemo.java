package goodalgorithms;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerDemo {
    private static volatile int cnt = 1;
    private static Lock lock = new ReentrantLock();
    private static Condition con1 = lock.newCondition();
    private static Condition con2 = lock.newCondition();
    private static Condition con3 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Worker w1 = new Worker(1, lock, con1, con2);
        Worker w2 = new Worker(2, lock, con2, con3);
        Worker w3 = new Worker(0, lock, con3, con1);
        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);
        Thread t3 = new Thread(w3);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    static class Worker implements Runnable {
        private int d;
        private Lock lock;
        private Condition waitCon, signalCon;
        Worker(int d, Lock lock, Condition waitCon, Condition signalCon) {
            this.d = d;
            this.lock = lock;
            this.waitCon = waitCon;
            this.signalCon = signalCon;
        }
        @Override
        public void run() {
            while (cnt <= 1000) {
                try {
                    lock.lock();
                    if (cnt % 3 != d) {
                        waitCon.await();
                    }
                    if (cnt <= 1000)
                        System.out.println(Thread.currentThread().getId() + " " + cnt++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    signalCon.signal();
                    lock.unlock();
                }
            }
        }
    }
}
