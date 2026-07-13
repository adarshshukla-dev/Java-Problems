class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        int m = nums1.length, n = nums2.length;
        int total = m + n;
        int p1 = 0, p2 = 0;
        int curr = 0, prev = 0;

        for (int i = 0; i <= total / 2; i++) 
        {
            prev = curr;
            if (p2 >= n || (p1 < m && nums1[p1] <= nums2[p2])) 
            {
                curr = nums1[p1++];
            } 
            else 
            {
                curr = nums2[p2++];
            }
        }
        return (total % 2 == 1) ? curr : (prev + curr) / 2.0;
    }
}