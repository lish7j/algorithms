package bishiti;



import sun.misc.ProxyGenerator;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class Main20 {
    interface Fly {
        void doN();
        int mul(int x, int y);
    }

    static class Adder implements Fly {

        @Override
        public void doN() {
            System.out.println("目标对象doN");
        }

        @Override
        public int mul(int x, int y) {
            System.out.println("目标对象mul");
            return x+y;
        }
    }

    static class AdderProxy implements InvocationHandler {
        private Object sub;
        public AdderProxy(Object sub) {
            this.sub = sub;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("代理对象before");
            Object o = method.invoke(sub, args);
            System.out.println("代理对象after");
            return o;
        }
    }

    public static void main(String[] args) throws IOException {

        Adder ad = new Adder();
        AdderProxy a = new AdderProxy(ad);
        ClassLoader cl = ad.getClass().getClassLoader();
        Fly s = (Fly)Proxy.newProxyInstance(cl, ad.getClass().getInterfaces(), a);
        Class cs = s.getClass();
        System.out.println(cs);
        Class cc = cs.getSuperclass();
        System.out.println(cc);
    }
}

