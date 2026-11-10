class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            long minCost = Long.MAX_VALUE;
            for (int jump = 1; jump <= 3; jump++) {
                if (i - jump >= 0) {
                    minCost = Math.min(minCost, dp[i - jump] + costs[i - 1] + (long) jump * jump);
                }
            }
            dp[i] = minCost;
        }

        return (int) dp[n];
    }
}