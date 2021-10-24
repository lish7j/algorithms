package bishiti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    private static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        String[] ss = null;
        s = br.readLine();
        ss = s.split(" ");
        int k = Integer.parseInt(ss[1]);
        char[] ch = ss[0].toCharArray();
        process(ch, 0, k);
        System.out.print(max);
    }

    private static void process(char[] arr, int index, int k) {
        if (k == 0 || index == arr.length) {
            int tmp = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '1') {
                    tmp++;
                    max = Math.max(max, tmp);
                } else
                    tmp = 0;
            }
            return;
        }
        for (int j = index; j < arr.length; j++) {
            if (arr[j] == '0') {
                arr[j] = '1';
                process(arr, index + 1, k - 1);
                arr[j] = '0';
            }
        }
    }
}
