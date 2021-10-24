package oldcode;

import java.util.Arrays;

public class Solution2 {
    public void func(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1)
            return;
        int evenIndex = 0, oddIndex = arr.length - 1;
        while (evenIndex < oddIndex) {
            while (evenIndex < arr.length && arr[evenIndex] % 2 == 0)
                evenIndex++;
            while (oddIndex > 0 && arr[oddIndex] % 2 == 1)
                oddIndex--;
            if (evenIndex < oddIndex) {
                int temp = arr[evenIndex];
                arr[evenIndex] = arr[oddIndex];
                arr[oddIndex] = temp;
                evenIndex++;
                oddIndex--;
            }
        }
        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
