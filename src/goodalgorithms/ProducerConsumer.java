package goodalgorithms;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    static class Producer implements Runnable {
        private String str;

        public Producer(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    queue.put(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            String str = null;
            while (true) {
                try {
                    str = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end with " + str);
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 30; i++)
            new Thread(new Producer(String.format("%d thread", i))).start();
        Consumer c = new Consumer();
        for (int i = 0; i < 20; i++)
            new Thread(c).start();
    }
}
