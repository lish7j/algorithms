package goodalgorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RedPackage {

    public static double[] split(int num, int people) {
        num *= 100;
        if (num / people < 1)
            throw new IllegalArgumentException("红包金额太少");
        int[] points = new int[people + 1];
        points[0] = 0; points[people] = num;
        Set<Integer> set = new HashSet<>();
        int remains = people - 1, index = 1;
        Random r = new Random();
        while (remains-- > 0) {
            int ri = r.nextInt(num - 1) + 1;
            if (!set.add(ri))
                continue;
            points[index++] = ri;
        }
        Arrays.sort(points);
        double[] redPack = new double[people];
        for (int i = 1; i < points.length; i++) {
            redPack[i - 1] = (points[i] - points[i - 1])  / 100.0;
        }
        return redPack;
    }
}
