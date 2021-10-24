package bishiti;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main5 {
    private static int ans = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] ss = null;
        int t = Integer.parseInt(s);
        int[][] arr = new int[t][];
        for (int i = 0; i < t; i++) {
            s = br.readLine();
            int n = Integer.parseInt(s);
            arr[i] = new int[n];
            s = br.readLine();
            ss = s.split(" ");
            for (int j = 0; j < ss.length; j++) {
                arr[i][j] = Integer.parseInt(ss[j]);
            }
        }
        int[] anss = new int[t];
        for (int i = 0; i < t; i++) {
            anss[i] = helper(arr[i]);
        }
        for (int i = 0; i < t; i++) {
            System.out.println(anss[i]);
        }

    }

    private static int helper(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            sum += arr[i];
            if (sum < 0) {
                int p = pq.poll();
                sum -= p;
            }
            ans = Math.max(ans, pq.size());
        }
        return ans;
    }

}