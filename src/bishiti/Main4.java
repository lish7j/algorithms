package bishiti;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
    private static int max = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        String[] ss = null;
        s = br.readLine();
        int[] ma = new int[23];
        int res = 0, t = 1;
        long ans = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            ma[res]++;
            res = (res + (s.charAt(i) - '0') * t) % 22;
            ans += ma[res];
            t = t * 10 ;
        }
        System.out.print(ans);
    }
}
