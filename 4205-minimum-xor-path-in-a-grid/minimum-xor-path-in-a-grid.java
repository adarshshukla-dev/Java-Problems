import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Set<Integer>[] prev = new Set[n];
        for (int j = 0; j < n; j++) {
            prev[j] = new HashSet<>();
        }
        
        prev[0].add(grid[0][0]);
        
        for (int j = 1; j < n; j++) {
            for (int val : prev[j - 1]) {
                prev[j].add(val ^ grid[0][j]);
            }
        }
        
        if (m == 1) {
            int minXor = Integer.MAX_VALUE;
            for (int val : prev[n - 1]) {
                minXor = Math.min(minXor, val);
            }
            return minXor;
        }
        
        for (int i = 1; i < m; i++) {
            Set<Integer>[] curr = new Set[n];
            for (int j = 0; j < n; j++) {
                curr[j] = new HashSet<>();
            }
            
            for (int j = 0; j < n; j++) {
                for (int val : prev[j]) {
                    curr[j].add(val ^ grid[i][j]);
                }
                if (j > 0) {
                    for (int val : curr[j - 1]) {
                        curr[j].add(val ^ grid[i][j]);
                    }
                }
            }
            prev = curr;
        }
        
        int minXor = Integer.MAX_VALUE;
        for (int val : prev[n - 1]) {
            minXor = Math.min(minXor, val);
        }
        
        return minXor;
    }
}