package pattern;



import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingleIns {
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {

            System.out.println(Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(1000);
                System.out.println("==" + Thread.currentThread().isInterrupted());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });
        t.start();
        //Thread.sleep(3);
        t.interrupt();

    }

    public static List<Integer> maxSubArray(int[] arr) {
        int start = 0, len = 1, ans = arr[0];
        int st = 0, le = 1, pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] + pre > arr[i]) {
                le++;
                pre += arr[i];
            } else {
                st = i;
                le = 1;
                pre = arr[i];
            }
            if (pre > ans) {
                ans = pre;
                start = st;
                len = le;
            }
        }
        List<Integer> result = new ArrayList<>(len);
        System.out.println(ans + " " + st + " " + len);
        for (int i = start; i < start + len; i++) {
            result.add(arr[i]);
        }
        return result;
    }
    abstract class exe {
        abstract void func();
    }
    interface sdd {
        static void func() {}
        abstract void func2();
        default void func22() {
            return;
        }
    }
}
