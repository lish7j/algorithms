import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static boolean[] visit;
    private static int[] min, max;
    private static int[] childnum;
    private static int[] weight;
    private static int node = -1, maxdiff = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = br.readLine().trim().split(" ");
        int N = Integer.parseInt(s1[0]), k = Integer.parseInt(s1[1]);
        weight = new int[N+1];
        visit = new boolean[N+1];
        childnum = new int[N+1];
        min = new int[N+1];
        max = new int[N+1];
        s1 = br.readLine().trim().split(" ");
        for (int i = 0; i < s1.length; i++) {
            weight[i+1] = Integer.parseInt(s1[i]);
        }
        List<List<Integer>> graph = new ArrayList<>(N+1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            s1 = br.readLine().trim().split(" ");
            int u = Integer.parseInt(s1[0]), v = Integer.parseInt(s1[1]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int root = Integer.parseInt(br.readLine());

        dfs(root, graph, k);
        System.out.println(node);


    }

    private static void dfs(int root, List<List<Integer>> graph, int k) {
        min[root] = weight[root];
        max[root] = weight[root];
        childnum[root] = 1;
        visit[root] = true;
        List<Integer> childs = graph.get(root);
        for (int child : childs) {
            if (!visit[child]) {
                dfs(child, graph, k);
                min[root] = Math.min(min[root], min[child]);
                max[root] = Math.max(max[root], max[child]);
                childnum[root] += childnum[child];
            }
        }

        if (childnum[root] <= k && max[root] - min[root] >= maxdiff) {
            if (max[root] - min[root] > maxdiff) {
                maxdiff = max[root] - min[root];
                node = root;
            } else {
                node = node == -1 ? root : Math.min(node, root);
            }
        }
    }
}
