import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] arrayRankTransform(int[] arr) 
    {
        int n = arr.length;
        if (n == 0) {
            return new int[0];
        }

        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);

        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        for (int num : sortedArr) 
        {
            if (!rankMap.containsKey(num)) 
            {
                rankMap.put(num, rank);
                rank = rank + 1;
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) 
        {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }
}