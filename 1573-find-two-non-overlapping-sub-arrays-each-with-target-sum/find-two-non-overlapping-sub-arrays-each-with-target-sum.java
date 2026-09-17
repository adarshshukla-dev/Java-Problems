import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        int result = Integer.MAX_VALUE;
        int currentMin = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) 
        {
            sum += arr[i];

            if (map.containsKey(sum - target)) 
            {
                int left = map.get(sum - target);
                int len = i - left;

                if (left >= 0 && minLen[left] != Integer.MAX_VALUE) 
                {
                    result = Math.min(result, len + minLen[left]);
                }

                currentMin = Math.min(currentMin, len);
            }

            minLen[i] = currentMin;
            map.put(sum, i);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}