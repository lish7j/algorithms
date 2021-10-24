package goodalgorithms;


import common.ListNode;
import common.TreeNode;
import java.util.*;

public class Leetcode {


    public static void main(String[] args) {
        Leetcode code = new Leetcode();
        System.out.println(code.indexOfSubstr("abcde", "bde"));
    }

    // 最大子数组和
    public int maxSubArray(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        int ans = arr[0];
        for (int i = 0; i < n; i++) {
            dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 只有小写字母 寻找最长的不重复子串长度
    public int findLongestNotRepeatSubstr(String s) {
        int[] ch = new int[26];
        int ans = 0, st = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            ch[index]++;
            while (st < index && ch[index] >= 2) {
                ch[s.charAt(st++) - 'a']--;
            }
            ans = Math.max(ans, i - st + 1);
        }
        return ans;
    }

    // leetcode 502
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        List<int[]> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(new int[]{profits[i], capital[i]});
        }
        Collections.sort(arr, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int st = 0;
        while (k-- > 0) {
            while (st < arr.size() && w >= arr.get(st)[1]) {
                pq.add(arr.get(st)[0]);
                st++;
            }
            if (pq.isEmpty())
                return w;
            w += pq.poll();
        }
        return w;
    }

    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int ans = 0, left = 0;
        int maxcnt = 0;
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'A']++;
            maxcnt = Math.max(maxcnt, count[s.charAt(i) - 'A']);
            while (i - left + 1 > maxcnt + k) {
                count[s.charAt(left++) - 'A']--;
            }
            System.out.println(i + " " + left + " " + ans + " " + maxcnt);
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    // 给一个正整数组，求出每个元素和后面第一个值比它大的数字之间的间隔，若是最大则是-1
    public int[] findDistance(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < ans.length; i++)
            ans[i] = -1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty())
                ans[i] = stack.peek() - i;
            stack.push(i);
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
        return ans;
    }

    public int[] findDistance2(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < ans.length; i++)
            ans[i] = -1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
        return ans;
    }


    // 使用冒泡排序链表
    public ListNode bubbleSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode tail = null, cur = head;
        boolean flag = false;
        while (cur.next != tail) {
            flag = false;
            while (cur.next != tail) {
                if (cur.val > cur.next.val) {
                    flag = true;
                    int tmp = cur.next.val;
                    cur.next.val = cur.val;
                    cur.val = tmp;
                }
                cur = cur.next;
            }
            if (!flag)
                break;
            tail = cur;
            cur = head;
        }
        return head;
    }

    // 打印链表的所有值
    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void PostSearch(TreeNode root) {
        if (root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                list.add(0, cur.val);
                cur = cur.left;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    public static void PreSearch(TreeNode root) {
        if (root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> arr = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode h = stack.pop();
            if (h.left != null) {
                stack.push(h.left);
            }
            if (h.right != null) {
                stack.push(h.right);
            }
            arr.add(h.val);
        }
    }

    // 104
    public int depthOfTree(TreeNode root) {
        if (root == null)
            return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            level++;
        }
        return level;
    }

    // 计算2^n
    public String calculate(int n) {
        if (n == 0)
            return "0";
        if (n == 1)
            return "2";
        String ans = "1";
        for (int i = 0; i < n; i++) {
            StringBuilder ss = new StringBuilder();
            char[] ch = ans.toCharArray();
            int carry = 0;
            for (char c : ch) {
                int now = (carry + (c - '0') * 2) % 10;
                carry = (carry + (c - '0') * 2) / 10;
                ss.append(now);
            }
            if (carry != 0)
                ss.append(carry);
            if (i < n - 1)
                ans = ss.toString();
            else
                ans = ss.reverse().toString();
        }
        return ans;
    }

    // 给定一个数组nums,nums[i]表示每个木头的长度,木头可以截断
    // 现在需要k根长度一样的木头,每根木头的最大长度是多少
    public int cutN(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int ans = 0;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            int cur = 0;
            for (int i : nums) {
                cur += i / mid;
            }
            if (cur >= k) {
                ans = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return ans;
    }

    // 对于一个有序数组中的元素k, 找出m个元素距离k最少, 距离是绝对值
    public int[] findNeighbors(int[] nums, int k, int m) {
        int[] ans = new int[m];
        int st = 0;
        int mid = -1, left = 0, right = nums.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == k) {
                break;
            } else if (nums[mid] < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int moveLeft = mid - 1, moveRight = mid + 1;
        while (st < m) {
            if (moveLeft >= 0 && moveRight < nums.length) {
                int dis1 = Math.abs(nums[mid] - nums[moveLeft]);
                int dis2 = Math.abs(nums[mid] - nums[moveRight]);
                if (dis1 <= dis2) {
                    ans[st++] = nums[moveLeft--];
                } else {
                    ans[st++] = nums[moveRight++];
                }
            } else if (moveLeft >= 0) {
                ans[st++] = nums[moveLeft--];
            } else {
                ans[st++] = nums[moveRight++];
            }
        }
        return ans;
    }

    public int partition(int[] nums, int left, int right, int k) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[left] > pivot)
                left++;
            while (left < right && nums[right] <= pivot)
                right--;

            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        if (left == k - 1) {
            return nums[left];
        } else if (left < k - 1) {
            return partition(nums, left + 1, right, k);
        }
        return partition(nums, 0, left - 1, k);
    }

    public int getFirstLeftNumber(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int n = nums.length;
        if (target < nums[0] || target > nums[n - 1])
            return -1;

        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        if (nums[left] != target)
            return -1;
        return left;
    }

    public int convertToInt(String s) {
        //char[] ch = new StringBuilder(s).reverse().toString().toCharArray();
        int ans = 0;
        int base = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                ans += (c - '0') * base;
            } else {
                ans += (c - 'A' + 10) * base;
            }
            base *= 16;
        }
        return ans;
    }

    public String compressStr(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        char pre = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                cnt++;
            } else {
                if (cnt > 1)
                    sb.append(cnt);
                sb.append(pre);
                pre = s.charAt(i);
                cnt = 1;
            }
        }
        if (cnt > 1)
            sb.append(cnt);
        sb.append(pre);
        return sb.toString();
    }

    // 给一个非空数组，返回数目最多的元素，如果有多个数目最多的元素，
    // 返回最短的子串长度（第一次出现到最后一次出现）
    static class Node {
        int cnt;
        int st, ed;

        @Override
        public String toString() {
            return "Node{" +
                    "cnt=" + cnt +
                    ", st=" + st +
                    ", ed=" + ed +
                    '}';
        }
    }

    public int maxNumber(int[] arr) {

        int ans = arr[0], max = 1;
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                Node n = new Node();
                n.cnt = 1;
                n.st = i;
                n.ed = i;
                map.put(arr[i], n);
            } else {
                Node n = map.get(arr[i]);
                n.cnt += 1;
                n.ed = i;
                if (n.cnt > max) {
                    ans = arr[i];
                    max = n.cnt;
                } else if (n.cnt == max) {
                    Node dix = map.get(ans);
                    if (n.ed - n.st < dix.ed - dix.st) {
                        ans = arr[i];
                    }
                }
            }
        }
        System.out.println(map);
        return ans;
    }

    // 有一个字符串 A ，你每次可以把其中一个字符，拿出来放到字符串末尾，
    // 问想转换成字符串 B,最少需要移动几次（保证 A 和 B 构成元素相同）
    public int moveLeast(String a, String b) {
        int index = 0;
        for (char c : a.toCharArray()) {
            if (b.charAt(index) == c)
                index++;
        }

        return a.length() - index;
    }

    // 最大连续子数组和
    public List<Integer> maxSubArray_2(int[] arr) {

        int start = 0, len = 1, ans = arr[0];
        int nowStart = 0, nowLen = 1, sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (sum > 0) {
                nowLen++;
                sum += arr[i];
            } else {
                nowStart = i;
                nowLen = 1;
                sum = arr[i];
            }
            if (sum > ans) {
                ans = sum;
                start = nowStart;
                len = nowLen;
            }
        }
        List<Integer> result = new ArrayList<>(len);
        System.out.println(ans + " " + nowStart + " " + len);
        for (int i = start; i < start + len; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public TreeNode findCommonParent(TreeNode root, int v1, int v2) {
        if (root.val == v1 || root.val == v2)
            return root;
        if (root.val < v1)
            return findCommonParent(root.right, v1, v2);
        if (root.val > v2)
            return findCommonParent(root.left, v1, v2);
        return root;
    }

    // 找出所有的子数组的和整除k
    public List<List<Integer>> findKSum(int[] arr, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = new int[arr.length + 1];
        for (int i = 1; i < arr.length; i++) {
            nums[i] = arr[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[j] - nums[i]) % k == 0) {
                    List<Integer> list = new ArrayList<>();
                    for (int x = i; x < j; x++) {
                        list.add(arr[x]);
                    }
                    result.add(list);
                }
            }
        }
        // System.out.println(result);
        return result;
    }

    // 找出所有的子数组的和整除k
    public int findKSum2(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int key = (sum % k + k) % k;
            int cnt = map.getOrDefault(key, 0);
            result += cnt;
            map.put(key, cnt + 1);
        }
        return result;
    }

    // 输入几个区间数组，输出覆盖的最大长度
    public double maxRange(double[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0])
                return a[1] > b[1] ? 1 : -1;
            return a[0] > b[0] ? 1 : -1;
        });
        double ans = 0;
        double end = arr[0][1], start = arr[0][0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i][0] <= end)
                end = Math.max(arr[i][1], end);
	        else {
                ans += end - start;
                start = arr[i][0];
                end = arr[i][1];
            }
        }

        return ans + end - start;
    }

    // 十六进制加法 没有符号位 如果有符号位的话 就需要实现一个减法
    // 特殊情况1 a正 b负 a负 b正 先检查结果是正还是负，
    // 就是说哪个数的绝对值更大就作为被减数
    public String hexAdd(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int c1 = i >= 0 ? fromHexToInt(a.charAt(i--)) : 0;
            int c2 = j >= 0 ? fromHexToInt(b.charAt(j--)) : 0;
            int sum = (c1 + c2 + carry) % 16;
            if (sum >= 10 && sum <= 15) {
                sum -= 10;
                sb.append((char)('A' + sum));
            } else {
                sb.append(sum);
            }
            carry = (c1 + c2 + carry) / 16;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    private int fromHexToInt(char c) {
        if (c >= 'a' && c <= 'f')
            return 10 + c - 'a';
        if (c >= 'A' && c <= 'F')
            return 10 + c - 'A';
        return c - '0';
    }

    public int trap(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        int[] left = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= stack.peek())
                stack.pop();
            if (stack.isEmpty())
                right[i] = 0;
            else
                right[i] = stack.peek();
            stack.push(nums[i]);
        }
        for (int i : right) {
            System.out.print(i + " ");
        }
        stack.clear();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] >= stack.peek())
                stack.pop();
            if (stack.isEmpty())
                left[i] = 0;
            else
                left[i] = stack.peek();
            stack.push(nums[i]);
        }
        System.out.println();
        for (int i : left) {
            System.out.print(i + " ");
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(Math.min(left[i], right[i]) - nums[i], 0);
        }
        return ans;
    }

    // 无重复子串的最大长度
    public int lengthOfLongestSubstring(String str) {
        int len = 0;
        int[] ch = new int[128];
        int left = 0;
        for (int i = 0; i < str.length(); i++) {
            while (left < i && ch[str.charAt(i)] == 1) {
                ch[str.charAt(left)]--;
                left++;
            }
            len = Math.max(len, i - left + 1);
            ch[str.charAt(i)]++;
        }
        return len;
    }

    // 无重复子串的最大长度
    public int lengthOfLongestSubstring2(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < last.length; i++) {
            last[i] = -1;
        }
        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }


    // 能否在字符串s找到一个子串与str重排序之后一样，返回子串的开始位置
    public int indexOfSubstr(String s, String str) {
        int[] need = new int[128];
        for (int i = 0; i < str.length(); i++) {
            need[str.charAt(i)]++;
        }
        int left = 0;
        int count = str.length();
        int start = -1, minLen = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (need[s.charAt(i)] > 0)
                count--;
            need[s.charAt(i)]--;
            if (count == 0) {
                while (left < i && need[s.charAt(left)] < 0) {
                    need[s.charAt(left)]++;
                    left++;
                }
                if (i - left + 1 < minLen) {
                    minLen = i - left + 1;
                    if (minLen == str.length()) {
                        start = left;
                    }
                }
                need[s.charAt(left)]++;
                left++;
                count++;
            }
        }
        return start;
    }

    // leetcode 84
    public int largestRectangleArea(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < tmp.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                area = Math.max(area, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }
        return area;
    }

}
