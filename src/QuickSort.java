import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class QuickSort {
  public static void main(String[] args) {
        int[] nums = new int[30];
        Random r = new Random();
        int xun = 30;
        while (xun > 0) {
            for (int i = 0; i < nums.length; i++)
                nums[i] = r.nextInt(10000);
            quicksort(nums);
            xun--;
        }

    }

    public static void quicksort(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        stack.push(nums.length - 1);
        int left, right, i, j;
        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();
            i = left; j = right;
            int temp = nums[left];
            while (i < j) {
                while (i < j && nums[j] > temp) {
                    j--;
                }
                nums[i] = nums[j];
                while (i < j && nums[i] <= temp) {
                    i++;
                }
                nums[j] = nums[i];
            }
            nums[i] = temp;
            if (i < right) {
                stack.push(i + 1);
                stack.push(right);
            }
            if (left < j) {
                stack.push(left);
                stack.push(j - 1);

            }
        }

        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
