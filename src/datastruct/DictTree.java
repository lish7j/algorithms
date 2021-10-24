package datastruct;

import java.util.PriorityQueue;

public class DictTree {
    static class TreeNode {
        int val;
        int cnt;
        TreeNode[] child;
        public TreeNode(int val) {
            this.val = val;
            this.child = new TreeNode[10];
        }
    }

    public static void main(String[] args) {
        System.out.println(func(13, 2));
    }

    public static int func(int n, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
            String x1 = String.valueOf(a), x2 = String.valueOf(b);
            return x2.compareTo(x1);
        });
        for (int i = 1; i <= n; i++) {
//            if (queue.size() >= k)
//                queue.poll();
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        return -1;
    }
}
