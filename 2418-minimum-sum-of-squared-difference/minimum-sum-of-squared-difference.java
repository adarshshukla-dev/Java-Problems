class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) 
        {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }

        if (maxDiff == 0) return 0;

        long[] count = new long[maxDiff + 1];
        for (int i = 0; i < n; i++) 
        {
            count[Math.abs(nums1[i] - nums2[i])]++;
        }

        for (int d = maxDiff; d > 0; d--) 
        {
            if (count[d] == 0) continue;

            if (totalK >= count[d]) 
            {
                totalK -= count[d];
                count[d - 1] += count[d];
                count[d] = 0;
            } 
            else 
            {
                count[d] -= totalK;
                count[d - 1] += totalK;
                totalK = 0;
                break;
            }
        }

        long sum = 0;
        for (int d = 1; d <= maxDiff; d++) 
        {
            if (count[d] > 0) 
            {
                sum += count[d] * (long) d * d;
            }
        }

        return sum;
    }
}