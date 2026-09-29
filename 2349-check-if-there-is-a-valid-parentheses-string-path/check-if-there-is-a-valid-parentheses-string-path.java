class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') 
        {
            return false;
        }

        int maxLen = m + n - 1;
        boolean[][][] visited = new boolean[m][n][maxLen + 1];

        return dfs(grid, 0, 0, 0, m, n, visited);
    }

    private boolean dfs(char[][] grid, int r, int c, int open, int m, int n, boolean[][][] visited) {
        open += (grid[r][c] == '(' ? 1 : -1);

        if (open < 0) 
        {
            return false;
        }

        if (r == m - 1 && c == n - 1) 
        {
            return open == 0;
        }

        if (visited[r][c][open])
        {
            return false;
        }
        visited[r][c][open] = true;

        if (r + 1 < m && dfs(grid, r + 1, c, open, m, n, visited)) 
        {
            return true;
        }

        if (c + 1 < n && dfs(grid, r, c + 1, open, m, n, visited)) 
        {
            return true;
        }

        return false;
    }
}