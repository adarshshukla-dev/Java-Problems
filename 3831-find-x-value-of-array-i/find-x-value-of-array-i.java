class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] prev = new long[k];

        for (int x : nums) 
        {
            int mod = x % k;
            long[] curr = new long[k];

            for (int r = 0; r < k; r++) 
            {
                if (prev[r] > 0) 
                {
                    int nextMod = (r * mod) % k;
                    curr[nextMod] += prev[r];
                }
            }

            curr[mod]++;

            for (int r = 0; r < k; r++) 
            {
                result[r] += curr[r];
            }

            prev = curr;
        }

        return result;
    }
}