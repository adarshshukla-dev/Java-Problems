import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) 
    {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k = k % total;

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) 
        {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) 
            {
                row.add(0);
            }
            result.add(row);
        }
        for (int i = 0; i < total; i++) 
        {
            int newIdx = (i + k) % total;
            int origRow = i / n, origCol = i % n;
            int newRow = newIdx / n, newCol = newIdx % n;

            result.get(newRow).set(newCol, grid[origRow][origCol]);
        }

        return result;
    }
}