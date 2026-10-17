import java.util.*;

class Solution {
    public int minSwaps(int[] nums) 
    {
        int n = nums.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) 
        {
            indices[i] = i;
        }

        int[] digitSums = new int[n];
        for (int i = 0; i < n; i++) 
        {
            digitSums[i] = getDigitSum(nums[i]);
        }

        Arrays.sort(indices, (a, b) -> 
        {
            if (digitSums[a] != digitSums[b]) 
            {
                return Integer.compare(digitSums[a], digitSums[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) 
        {
            if (visited[i] || indices[i] == i) 
            {
                continue;
            }

            int cycleSize = 0;
            int curr = i;

            while (!visited[curr])
            {
                visited[curr] = true;
                curr = indices[curr];
                cycleSize++;
            }

            if (cycleSize > 1) 
            {
                swaps += cycleSize - 1;
            }
        }

        return swaps;
    }

    private int getDigitSum(int val) 
    {
        int sum = 0;
        while (val > 0) 
        {
            sum += val % 10;
            val /= 10;
        }
        return sum;
    }
}