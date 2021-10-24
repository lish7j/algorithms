package bishiti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lazada {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 1000000;
        boolean[] pi = new boolean[n + 1];
        pi[0] = true;
        pi[1] = true;
        for (int i = 2; i *i  <= n; i++) {
            if (!pi[i]) {
                for (int j = i; i * j <= n; j++)
                    pi[i * j] = true;
            }
        }
        int[] cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!pi[i]) {
                cnt[i] = cnt[i - 1] + 1;
            } else {
                cnt[i] = cnt[i - 1];
            }
        }
        String s = null;
        while ((s = br.readLine()) != null) {
            int m = Integer.parseInt(s);
            String[] ss = null;
            int[] ans = new int[m];
            for (int i = 0; i < m; i++) {
                ss = br.readLine().split(" ");
                int l = Integer.parseInt(ss[0]);
                int r = Integer.parseInt(ss[1]);
                if (l >= 1)
                    l -= 1;
                ans[i] = cnt[r] - cnt[l];
            }
            for (int i = 0; i < m; i++) {
                System.out.println(ans[i]);
            }
        }
    }
}
