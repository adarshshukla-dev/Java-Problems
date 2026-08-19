import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }

        int totalGroups = (n - rowMasks.size()) * 2;

        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5); 
        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);  
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7); 

        for (int mask : rowMasks.values()) {
            boolean leftClear = (mask & leftMask) == 0;
            boolean rightClear = (mask & rightMask) == 0;

            if (leftClear && rightClear) {
                totalGroups += 2;
            } else if (leftClear || rightClear || (mask & middleMask) == 0) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}