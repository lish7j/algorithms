package goodalgorithms;

import java.util.ArrayDeque;
import java.util.Deque;

public class MatchSymbolWithPriority {
    public static void main(String[] args) {
        System.out.println(isMatch("{[()}{}"));
        System.out.println(isMatch("({})"));
        System.out.println(isMatch("[()]"));
        System.out.println(isMatch("([])"));
    }
    public static boolean isMatch(String symbolStr) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (char c : symbolStr.toCharArray()) {
            switch (c) {
                case '{':
                    if (!stack.isEmpty() && stack.peek() < 3)
                        return false;
                    stack.push(3);
                    break;
                case '[':
                    if (!stack.isEmpty() && stack.peek() < 2)
                        return false;
                    stack.push(2);
                    break;
                case '(':
                    stack.push(1);
                    break;
                case '}':
                    if (stack.peek() != 3)
                        return false;
                    stack.pop();
                    break;
                case ']':
                    if (stack.peek() != 2)
                        return false;
                    stack.pop();
                    break;
                case ')':
                    if (stack.peek() != 1)
                        return false;
                    stack.pop();
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
