package oldcode;// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.sql.DriverManager;
import java.util.*;
import java.io.*;


public class Main2 {

    public static void main(String[] args) throws IOException {
        int[] arr = {1,2,3,2,1,0,-1,0,1};
        int target = 0, k = 1;
        List<Integer> ans = new ArrayList<>();
        int now = 0, n  = arr.length;
        while (now < n) {
            int step = Math.abs(arr[now] - target) / k;
            if (step == 0) {
                ans.add(now);
                now++;
            } else {
                now += step;
            }
        }
        System.out.println(ans);
    }
}