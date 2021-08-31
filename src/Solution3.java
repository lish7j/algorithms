public class Solution3 {
    public int findMin(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (arr[right] > arr[mid]) {
                right = mid;
            } else if (arr[right] < arr[mid]){
                left = mid + 1;
            } else {
                right--;
            }
        }
        return arr[left];
    }
}
