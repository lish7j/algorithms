package sorts;

import java.util.Random;

public class Helper {
    public static int[] generateArray(int n, int bound) {
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(bound);
        }
        return arr;
    }

    public static boolean isEquals(int[] a, int[] b) {
        assert a.length == b.length;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                return false;
        }
        return true;
    }

    public static long startTest() {
        return System.currentTimeMillis();
    }

    public static void printPerformance(long start) {
        long cost = System.currentTimeMillis() - start;
        System.out.println(cost);
    }
}
