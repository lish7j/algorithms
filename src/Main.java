import datastruct.BinarySortTree;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.*;



public class Main {

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        m.spiralOrder(arr);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int u = 0, d = matrix.length - 1;
        int l = 0, r = matrix[0].length - 1;
        while (l <= r && u <= d) {
            for (int i = l; i <= r; i++) {
                result.add(matrix[u][i]);
                System.out.print(matrix[u][i] + " ");
            }
            u++;
            System.out.println(String.format("\n-1 %d %d %d %d", l, r, u, d));
            for (int i = u; i <= d; i++) {
                result.add(matrix[i][r]);
                System.out.print(matrix[i][r] + " ");
            }
            r--;
            System.out.println(String.format("\n-2 %d %d %d %d", l, r, u, d));
            for (int i = r; i >= l; i--) {
                result.add(matrix[d][i]);
                System.out.print(matrix[d][i] + " ");
            }
            d--;
            System.out.println(String.format("\n-3 %d %d %d %d", l, r, u, d));
            for (int i = d; i >= u; i--) {
                result.add(matrix[i][l]);
                System.out.print(matrix[i][l] + " ");
            }
            System.out.println(String.format("\n-4 %d %d %d %d", l, r, u, d));
            l++;
        }

        return result;

    }

    static class HeapSort {
        int[] arr;
        int n;
        public HeapSort(int[] arr) {
            this.arr = arr;
            this.n = arr.length;
            buildHeap();
        }

        public int getMax() {
            int max = arr[0];
            arr[0] = arr[n - 1];
            n--;
            shiftDown(0);
            return max;
        }

        private void buildHeap() {
            for (int i = n - 1; i >= 0; i--) {
                shiftDown(i);
            }
        }

        private void shiftDown(int i) {
            while (2 * i + 1 < n) {
                int max = 2 * i + 1;
                int right = 2 * i + 2;
                if (right < n && arr[max] < arr[right]) {
                    max = right;
                }
                if (arr[i] > arr[max]) {
                    break;
                } else {
                    swap(i, max);
                    i = max;
                }
            }

        }

        private void shiftUp(int i) {
            while (i > 0) {
                int par = (i - 1) / 2;
                if (i > arr[par]) {
                    swap(i, par);
                    i = par;
                } else {
                    break;
                }
            }
        }

        private void swap(int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
