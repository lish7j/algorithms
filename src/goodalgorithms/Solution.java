package goodalgorithms;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {};
        int[] ss = s.searchRange(arr, 1);
        System.out.println(ss[0] + " " + ss[1]);
    }
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            res[0] = -1;
            res[1] = -1;
            return res;
        }

        int left = getFirstLeftNumber(nums, target);
        System.out.println("+==");
        if (left == -1) {
            return new int[]{-1,-1};
        }
        int right = searchRight(nums, target);
        return new int[]{left, right};

    }

    private int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 小于一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 下一轮搜索区间是 [left, mid]
                right = mid + 1;
            } else {
                // nums[mid] > target，下一轮搜索区间是 [left, mid - 1]
                right = mid - 1;
            }
        }

        if (nums[left] == target) {
            return left;
        }
        return -1;
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

    public int searchRight(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi ) {
            int mid = (lo + hi) / 2;
            if (nums[mid] == target)
                lo = mid;
            else if (nums[mid] > target)
                hi = mid - 1;
            else
                lo = mid + 1;
            System.out.println(lo + " " + hi);
        }

        return lo;

    }
}