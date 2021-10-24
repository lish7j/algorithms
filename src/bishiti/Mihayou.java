package bishiti;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mihayou {
    private static int N = 10;
    private static volatile int M = 100;
    private static double P = 0.001;

    // 用户剩余点击按钮次数
    private static Map<Integer, Integer> idCanButton = new ConcurrentHashMap<>();
    // 中奖用户
    private static Map<Integer, Integer> champions = new ConcurrentHashMap<>();
    private static Lock lock = new ReentrantLock();
    private static Random r = new Random();

    enum STATUS {
        STATUS_SUCCESS, STATUS_FAILED;
    }

    public static void main(String[] args) throws IOException {

    }



    public static STATUS Lottery(int id) {
        if (M == 0)
            return STATUS.STATUS_FAILED;
        if (champions.containsKey(id))
            return STATUS.STATUS_FAILED;

        if (!idCanButton.containsKey(id)) {
            idCanButton.put(id, N - 1);
        } else {
            int n = idCanButton.get(id);
            if (n == 0)
                return STATUS.STATUS_FAILED;
            idCanButton.put(id, n - 1);
        }
        int n = r.nextInt(1000);
        try {
            lock.lock();
            // 0.001概率抽中或者最后一次点击按钮也算抽中（保底策略）
            if (n == 0 || idCanButton.get(id) == 0) {
                M--;
                champions.put(id, id);
                return STATUS.STATUS_SUCCESS;
            } else {
                return STATUS.STATUS_FAILED;
            }
        } finally {
            lock.unlock();
        }
    }
}
