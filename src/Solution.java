import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Solution {
    static class A {
        private int x;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        @Override
        public String toString() {
            return "A{" +
                    "x=" + x +
                    '}';
        }
    }
    static class B extends A {
        private int y;


        public void fun(List<String> ax) {}
        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            String str = super.toString() +
            " B{" +
                    "y=" + y +
                    '}';
            return str;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        A b = new B();
        b.setX(10);
        
        Field xx = A.class.getDeclaredField("x");
        xx.setAccessible(true);
        System.out.println(xx.get(b));
        xx.set(b, 100);
        System.out.println(b.getX());
    }
}