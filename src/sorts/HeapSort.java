package sorts;

public class HeapSort {

    public static int[] sort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            shiftDown(arr, 0, i);
        }
        return arr;
    }

    private static void buildHeap(int[] arr) {
        for (int i = arr.length / 2; i >= 0; i--)
            shiftDown(arr, i, arr.length);
    }

    private static void shiftDown(int[] arr, int i, int n) {
        while (2 * i < n) {
            int maxIndex = 2 * i;
            if (2 * i + 1 < n && arr[2 * i + 1] > arr[maxIndex]) {
                maxIndex = 2 * i + 1;
            }
            if (arr[i] < arr[maxIndex]) {
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
                i = maxIndex;
            } else
                break;
        }
    }
}
