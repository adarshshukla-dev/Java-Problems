import java.util.*;

class Solution {
    public int subsequencePairCount(int[] nums) 
    {
        int MOD = 1_000_000_007, maxVal = 200;
        
        int[] freq = new int[maxVal + 1];
        for (int num : nums) freq[num]++;
        
        long[][] C = new long[201][201];
        for (int i = 0; i <= 200; i++) 
        {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) 
            {
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
            }
        }
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1;

        for (int x = 1; x <= maxVal; x++) 
        {
            if (freq[x] == 0) continue;

            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            int count = freq[x];

            long[][] ways = new long[count + 1][count + 1];
            for (int i = 0; i <= count; i++) 
            {
                for (int j = 0; j <= count - i; j++) 
                {
                    ways[i][j] = (C[count][i] * C[count - i][j]) % MOD;
                }
            }

            for (int g1 = 0; g1 <= maxVal; g1++) 
            {
                for (int g2 = 0; g2 <= maxVal; g2++) 
                {
                    if (dp[g1][g2] == 0) continue;

                    long currentWays = dp[g1][g2];

                    for (int i = 0; i <= count; i++) 
                    {
                        for (int j = 0; j <= count - i; j++) 
                        {
                            if (ways[i][j] == 0) continue;

                            int n1 = (i == 0) ? g1 : (g1 == 0 ? x : gcd(g1, x));
                            int n2 = (j == 0) ? g2 : (g2 == 0 ? x : gcd(g2, x));

                            long totalCombinations = (currentWays * ways[i][j]) % MOD;
                            nextDp[n1][n2] = (int) ((nextDp[n1][n2] + totalCombinations) % MOD);
                        }
                    }
                }
            }
            dp = nextDp;
        }
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) 
        {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }
        return (int) totalPairs;
    }

    private int gcd(int a, int b) 
    {
        return b == 0 ? a : gcd(b, a % b);
    }
}