package goodalgorithms;

import java.util.*;

public class Leetcode726 {
    public String countOfAtoms(String s) {
        int i = 0, len = s.length();
        Deque<String> st1 = new ArrayDeque<>();
        Deque<String> st2 = new ArrayDeque<>();
        while (i < len) {
            char c = s.charAt(i);
            if (c == '(') {
                st1.push(String.valueOf(c));
                i++;
            } else if (isBig(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                i++;
                while (i < len && isSmall(s.charAt(i))) {
                    sb.append(s.charAt(i++));
                }
                int cnt = 0;
                if (i < len && isNumber(s.charAt(i))) {
                    while (i < len && isNumber(s.charAt(i))) {
                        cnt = cnt * 10 + s.charAt(i++) - '0';
                    }
                }
                sb.append(" ");
                if (cnt == 0)
                    sb.append(1);
                else
                    sb.append(cnt);
                st1.push(sb.toString());
            } else {
                i++;
                int cnt = 0;
                if (i < len && isNumber(s.charAt(i))) {
                    while (i < len && isNumber(s.charAt(i))) {
                        cnt = cnt * 10 + s.charAt(i++) - '0';
                    }
                }
                if (cnt == 0)
                    cnt = 1;
                while (!st1.isEmpty()) {
                    String ss = st1.pop();
                    if (ss.equals("("))
                        break;
                    String[] sss = ss.split(" ");
                    int k = Integer.parseInt(sss[1]);
                    st2.push(sss[0] + " " + k * cnt);
                }
                while (!st2.isEmpty()) {
                    st1.push(st2.pop());
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        while (!st1.isEmpty()) {
            String[] ss = st1.pop().split(" ");
            int a = map.getOrDefault(ss[0], 0);
            map.put(ss[0], Integer.parseInt(ss[1]) + a);
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            StringBuilder sx = new StringBuilder();
            sx.append(e.getKey());
            if (e.getValue() > 1)
                sx.append(e.getValue());
            list.add(sx.toString());
        }
        list.sort(String::compareTo);
        for (String sx : list) {
            ans.append(sx);
        }
        return ans.toString();
    }

    private boolean isBig(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isSmall(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
}
