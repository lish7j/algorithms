package oldcode;// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.sql.DriverManager;
import java.util.*;
import java.io.*;

public class Main1 {
    private int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        String[] ss = null;
        s = br.readLine();
        ss = s.split(",");
        int M = Integer.parseInt(ss[0]), N = Integer.parseInt(ss[1]);
        s = br.readLine();
        ss = s.split(" ");
        int[][] arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(ss[i * M + j]);
            }
        }

        int[][] dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0 && dp[i][j] != Integer.MAX_VALUE) {
                    if (i + 1 < arr.length) {
                        solve(arr, dp, i + 1, j, arr[i][j], dp[i][j] + 1);
                    }
                    if (j + 1 < arr[0].length) {
                        solve(arr, dp, i, j + 1, arr[i][j], dp[i][j] + 1);
                    }
                }
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        if (dp[M - 1][N - 1] != Integer.MAX_VALUE)
            System.out.print(dp[M - 1][N - 1]);
        else
            System.out.print(-1);
    }

    private static void solve(int[][] arr, int[][] dp, int i, int j, int res, int jump) {
        if (res == 0 || arr[i][j] == 0) {
            return;
        }
        dp[i][j] = Math.min(dp[i][j], jump);
        if (i + 1 < arr.length) {
            solve(arr, dp, i + 1, j, res - 1, jump);
        }
        if (j + 1 < arr[0].length) {
            solve(arr, dp, i, j + 1, res - 1, jump);
        }
    }
}