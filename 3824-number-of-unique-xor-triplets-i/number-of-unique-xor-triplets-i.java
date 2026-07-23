class Solution {
    public int uniqueXorTriplets(int[] nums) 
    {
        int n = nums.length;
        if (n == 1) return 1;
        if (n == 2) return 2;

        if ((n & (n + 1)) == 0) 
        {
            return n + 1;
        }

        int nextPowerOfTwo = Integer.highestOneBit(n) << 1;
        return nextPowerOfTwo;
    }
}