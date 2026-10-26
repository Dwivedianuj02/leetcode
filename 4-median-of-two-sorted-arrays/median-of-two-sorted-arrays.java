
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        int[] ans = new int[n + m];

        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (nums1[i] < nums2[j]) {
                ans[k] = nums1[i];
                i++;
            } else {
                ans[k] = nums2[j];
                j++;
            }
            k++;
        }

        while (i < n) {
            ans[k] = nums1[i];
            i++;
            k++;
        }
        while (j < m) {
            ans[k] = nums2[j];
            j++;
            k++;
        }

        int len = ans.length;

        if (len % 2 == 0) {
            return ((double) ans[len / 2 - 1] + ans[len / 2]) / 2;
        }

        return ans[len / 2];
    }
}
