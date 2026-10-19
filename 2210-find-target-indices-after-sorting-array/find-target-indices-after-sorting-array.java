import java.util.*;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) 
    {
        int countLessThan = 0;
        int countTarget = 0;

        for (int num : nums) 
        {
            if (num < target) 
            {
                countLessThan++;
            } 
            else if (num == target) 
            {
                countTarget++;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < countTarget; i++) 
        {
            result.add(countLessThan + i);
        }

        return result;
    }
}
