package oldcode;

import java.util.concurrent.locks.LockSupport;

public class Normal {
    public static void main(String[] args) {
        int x = init();
        LockSupport.park();
    }

    public static int init() {
        try {
            System.out.println("==");
            return 10;
        } finally {
            System.out.println("finally=");
            return 100;
        }

    }
}
