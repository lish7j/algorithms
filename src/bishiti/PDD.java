package bishiti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PDD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        String[] strs = null;
        s = br.readLine();
        int T = Integer.parseInt(s);
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            s = br.readLine();
            strs = s.split(" ");
            int n = Integer.parseInt(strs[0]), m = Integer.parseInt(strs[1]);
            int x = Integer.parseInt(strs[2]), y = Integer.parseInt(strs[3]);
            int[] op = new int[n];
            if (n > 0) {
                s = br.readLine();
                strs = s.split(" ");
                for (int j = 0; j < n; j++)
                    op[j] = Integer.parseInt(strs[j]);
            }
            int[][] qizi = new int[m][2];
            for (int j = 0; j < m; j++)  {
                s = br.readLine();
                strs = s.split(" ");
                qizi[j][0] = Integer.parseInt(strs[0]);
                qizi[j][1] = Integer.parseInt(strs[1]);
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (op[j] == 1) {
                        qizi[k][0] = qizi[k][0] > 1 ? qizi[k][0] - 1 : qizi[k][0];
                    } else if (op[j] == 2) {
                        qizi[k][1] = qizi[k][1] > 1 ? qizi[k][1] - 1 : qizi[k][1];
                    } else if (op[j] == 3) {
                        qizi[k][0] = qizi[k][0] < n ? qizi[k][0] + 1 : qizi[k][0];
                    } else if (op[j] == 4) {
                        qizi[k][1] = qizi[k][1] < m ? qizi[k][1] + 1 : qizi[k][1];
                    }
                }
            }
            for (int j = 0; j < m; j++) {
                ans.add(new int[]{qizi[j][0], qizi[j][1]});
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            if (i < ans.size() - 1)
                System.out.println(ans.get(i)[0] + " " + ans.get(i)[1]);
            else
                System.out.print(ans.get(i)[0] + " " + ans.get(i)[1]);
        }
    }
}
