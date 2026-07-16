import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) 
    {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int maxSoFar = 0;
        for (int i = 0; i < n; i++) 
        {
            maxSoFar = Math.max(maxSoFar, nums[i]);
            prefixGcd[i] = gcd(nums[i], maxSoFar);
        }
        Arrays.sort(prefixGcd);
        long totalSum = 0;
        int left = 0, right = n - 1;
        while (left < right) 
        {
            totalSum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        return totalSum;
    }
    private int gcd(int a, int b) 
    {
        while (b != 0) 
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}