import java.util.*;

class Solution 
{
    public int sortableIntegers(int[] nums) 
    {
        int n = nums.length;
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) 
        {
            if (n % i == 0) 
            {
                divisors.add(i);
                if (i * i != n) 
                {
                    divisors.add(n / i);
                }
            }
        }

        int totalSum = 0;

        for (int k : divisors) 
        {
            if (isSortable(nums, n, k)) 
            {
                totalSum += k;
            }
        }

        return totalSum;
    }

    private boolean isSortable(int[] nums, int n, int k) 
    {
        int numBlocks = n / k;
        int prevMax = Integer.MIN_VALUE;

        for (int b = 0; b < numBlocks; b++) 
        {
            int start = b * k;
            int minVal = nums[start];
            int maxVal = nums[start];

            for (int i = start + 1; i < start + k; i++) 
            {
                minVal = Math.min(minVal, nums[i]);
                maxVal = Math.max(maxVal, nums[i]);
            }

            if (minVal < prevMax) 
            {
                return false;
            }

            int dropCount = 0;
            for (int i = start; i < start + k; i++) 
            {
                int next = start + (i - start + 1) % k;
                if (nums[i] > nums[next]) 
                {
                    dropCount++;
                }
            }

            if (dropCount > 1) 
            {
                return false;
            }

            prevMax = maxVal;
        }

        return true;
    }
}