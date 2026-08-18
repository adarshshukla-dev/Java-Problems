import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if (k == n) 
        {
            int maxVal = -1;
            for (int num : nums) 
            {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        if (k == 1) 
        {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int num : nums) 
            {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) 
            {
                if (entry.getValue() == 1) 
                {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }

        Map<Integer, Integer> subarrayCounts = new HashMap<>();

        for (int i = 0; i <= n - k; i++) 
        {
            Set<Integer> uniqueInSubarray = new HashSet<>();
            for (int j = i; j < i + k; j++) 
            {
                uniqueInSubarray.add(nums[j]);
            }
            for (int num : uniqueInSubarray) 
            {
                subarrayCounts.put(num, subarrayCounts.getOrDefault(num, 0) + 1);
            }
        }

        int maxVal = -1;
        for (Map.Entry<Integer, Integer> entry : subarrayCounts.entrySet()) 
        {
            if (entry.getValue() == 1) 
            {
                maxVal = Math.max(maxVal, entry.getKey());
            }
        }

        return maxVal;
    }
}