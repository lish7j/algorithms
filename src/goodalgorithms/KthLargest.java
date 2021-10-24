package goodalgorithms;

import java.util.PriorityQueue;

public class KthLargest {

    private PriorityQueue<Integer> minHeap;

    private int k;
    public KthLargest(int k, int[] arr) {
        this.k = k;
        this.minHeap = new PriorityQueue<>((a, b) -> a - b);

        for (int i = 0; i < arr.length; i++) {
            if (minHeap.size() < k) {
                minHeap.offer(arr[i]);
            } else if (arr[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(arr[i]);
            }
        }
    }

    public int add(int num) {
        if (num > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(num);
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 7, 8};
        KthLargest kth = new KthLargest(3, arr);
        for (int i = 20; i >= 10; i--) {
            System.out.println(kth.add(i));
        }
    }
}
