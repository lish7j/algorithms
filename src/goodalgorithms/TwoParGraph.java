package goodalgorithms;

import java.util.ArrayList;
import java.util.List;

public class TwoParGraph {
    private List<Integer>[] graph;
    private int[] colors;
    private boolean[] visited;

    // 假设顶点分别在0 - n-1
    public boolean isBiPar(int n, int[][] edges) {
        graph = new List[n];
        colors = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            if (graph[u] == null)
                graph[u] = new ArrayList<>();
            if (graph[v] == null)
                graph[v] = new ArrayList<>();
            graph[u].add(v);
            graph[v].add(u);
        }
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (!dfs(i, c) && !dfs(i, 1 - c))
                    return false;
            }
        }
        return true;
    }

    private boolean dfs(int u, int color) {
        colors[u] = color;
        visited[u] = true;
        for (int i = 0; i < graph[u].size(); i++) {
            int v = graph[u].get(i);
            if (visited[v]) {
                if (colors[v] == colors[u])
                    return false;
            } else if (!dfs(v, 1 - color)) {
                return false;
            }
        }
        return true;
    }
}
