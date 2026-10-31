import java.util.*;

class Solution 
{
    private int m, n, MOD = 1_000_000_007;
    private int[][][] memo;
    private int[][] grid;

    public int uniquePaths(int[][] grid) 
    {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.memo = new int[m][n][2];
        for (int[][] mat : memo) 
        {
            for (int[] row : mat) Arrays.fill(row, -1);
        }
        return (solve(0, 0, 0) + solve(0, 0, 1)) % MOD;
    }

    private int solve(int r, int c, int dir) 
    {
        int nr = r + (dir == 1 ? 1 : 0);
        int nc = c + (dir == 0 ? 1 : 0);

        if (nr < 0 || nr >= m || nc < 0 || nc >= n) return 0;

        int nDir = dir;
        while (grid[nr][nc] == 1) 
        {
            nDir = 1 - nDir;
            nr += (nDir == 1 ? 1 : 0);
            nc += (nDir == 0 ? 1 : 0);
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) return 0;
        }

        if (nr == m - 1 && nc == n - 1) return 1;
        if (memo[nr][nc][nDir] != -1) return memo[nr][nc][nDir];

        long res = (solve(nr, nc, 0) + solve(nr, nc, 1)) % MOD;
        return memo[nr][nc][nDir] = (int) res;
    }
}
