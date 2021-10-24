package goodalgorithms;

import java.lang.reflect.Field;

public class Algs {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s1 = new String("abc");
        String s2 = "abc";
        System.out.println(s1 == s2);

    }

    public static void menu() {
        int N = 2, M = 6;
        int[][] dp = new int[N+1][M+1];
        for (int i = 0;i < N+1; i++) {
            for (int j = 0; j < M+1; j++)
                dp[i][j] = Integer.MAX_VALUE;
        }
        for (int n = 1; n <= N; n++) {
            for (int j = n; j <= M; j++) {
                for (int k = 1; k <= 6; k++)
                    if (n == 1)
                        dp[n][j] = 1;
                    else if (j - k >= 0 && j - k <= (n-1)*6)
                        dp[n][j] += dp[n - 1][j - k];
            }
        }
        System.out.println(dp[N][M]);
    }


    private static int dfs(int[][] dp, int n, int m) {
        if (n == 1) {
            dp[n][m] = 1;
            return 1;
        }
        int sum = 0;
        if (dp[n][m] != Integer.MAX_VALUE)
            return dp[n][m];
        for (int i = 1; i <= 6; i++)
            if (m - i >= 1 && m - i <= (n - 1) * 6)
                sum += dfs(dp, n - 1, m - i);

        return dp[n][m] = sum;
    }
}
