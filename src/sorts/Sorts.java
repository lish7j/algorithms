package sorts;


import java.util.Arrays;

public class Sorts {

    public static void main(String[] args)  {

        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 20; j++) {
                int[] arr = Helper.generateArray((int) Math.pow(10, i), (int) Math.pow(10, i));
                int[] newArr = Arrays.copyOf(arr, arr.length);
                long st = Helper.startTest();
                int[] mySort = CountSort(arr);
                Helper.printPerformance(st);
                long st2 = Helper.startTest();
                Arrays.sort(newArr);
                Helper.printPerformance(st2);
                if (!Helper.isEquals(mySort, newArr)) {
                    System.out.println("Wrong answer " + i);
                } else {
                    System.out.println("Success " + i);
                }
                System.out.println("=======");
            }
        }
    }


    public static int[] BubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] InsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                int now = i, nowVal = arr[i];
                while (now > 0 && nowVal < arr[now - 1]) {
                    arr[now] = arr[now - 1];
                    now--;
                }
                arr[now] = nowVal;
            }
        }
        return arr;
    }

    public static int[] SelectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] QuickSort(int[] arr, int left, int right) {
        int pivot = arr[left], start = left, end = right;
        while (start < end) {
            while (start < end && arr[end] > pivot)
                end--;
            while (start < end && arr[start] <= pivot)
                start++;
            if (start < end) {
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
            }
        }
        int tmp = arr[start];
        arr[start] = arr[left];
        arr[left] = tmp;

        if (start > left) {
            QuickSort(arr, left, start - 1);
        }
        if (end < right)
            QuickSort(arr, end + 1, right);
        return arr;
    }

    public static int[] CountSort(int[] arr) {
        int min, max;
        min = arr[0]; max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        int[] count = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        int[] tmp = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            count[arr[i] - min]--;
            tmp[count[arr[i] - min]] = arr[i];
        }
        System.arraycopy(tmp, 0, arr, 0, tmp.length);
        return arr;
    }

    public static int[] GuibingSort(int[] nums, int start, int end) {
        if (start >= end)
            return new int[]{nums[start]};
        int mid = start + (end - start) / 2;
        int[] left = GuibingSort(nums, start, mid);
        int[] right = GuibingSort(nums, mid + 1, end);
        int[] ans = new int[end - start + 1];
        int index = 0, l = 0, r = 0;
        for (int i = 0; i < ans.length; i++) {
            if (r >= right.length || (l < left.length && left[l] <= right[r])) {
                ans[i] = left[l++];
                continue;
            }
            if (l >= left.length || right[r] < left[l]) {
                ans[i] = right[r++];
            }
        }
        return ans;
    }

}
