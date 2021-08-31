import java.util.*;

public class FindLongestAscSeq {
    public static void main(String[] args) {
        int[] arr = {1, 2, 8, 4, 6, 3, 4, 5};
        func(arr);
    }
    public static List<Integer> func(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();
        for (int i : arr) {
            while (!stack.isEmpty() && stack.peek() > i) {
                stack.pop();
            }
            stack.push(i);
            if (stack.size() > ans.size()) {
                ans = new ArrayList<>(stack);
                Collections.reverse(ans);
            } else if (stack.size() == ans.size() && stack.peek() < ans.get(ans.size() - 1)) {
                ans = new ArrayList<>(stack);
                Collections.reverse(ans);
            }
        }
        System.out.println(ans);
        return ans;
    }
}
