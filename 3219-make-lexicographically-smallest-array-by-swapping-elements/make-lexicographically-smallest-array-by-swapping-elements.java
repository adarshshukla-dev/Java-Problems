import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        List<Queue<Integer>> groups = new ArrayList<>();
        Map<Integer, Integer> numToGroup = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] - sorted[i - 1] > limit) {
                groups.add(new LinkedList<>());
            }
            groups.get(groups.size() - 1).offer(sorted[i]);
            numToGroup.put(sorted[i], groups.size() - 1);
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int groupIdx = numToGroup.get(nums[i]);
            result[i] = groups.get(groupIdx).poll();
        }

        return result;
    }
}