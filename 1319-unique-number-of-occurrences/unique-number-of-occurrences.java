import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        for (int num : arr) 
        {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        Set<Integer> uniqueFreqs = new HashSet<>(freqMap.values());
        
        return uniqueFreqs.size() == freqMap.size();
    }
}