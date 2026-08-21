class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long minCoin = Long.MAX_VALUE;
        for (int coin : coins) 
        {
            minCoin = Math.min(minCoin, coin);
        }
        long high = minCoin * k;
        long ans = high;

        while (low <= high) 
        {
            long mid = low + (high - low) / 2;
            if (countMultiples(coins, mid) >= k) 
            {
                ans = mid;
                high = mid - 1;
            } 
            else 
            {
                low = mid + 1;
            }
        }

        return ans;
    }

    private long countMultiples(int[] coins, long target) {
        long count = 0;
        int n = coins.length;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) 
        {
            long currentLcm = 1;
            int subsetBits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) 
            {
                if ((mask & (1 << i)) != 0) 
                {
                    subsetBits++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > target) 
                    {
                        overflow = true;
                        break;
                    }
                }
            }

            if (!overflow) 
            {
                if (subsetBits % 2 == 1) 
                {
                    count += target / currentLcm;
                } 
                else 
                {
                    count -= target / currentLcm;
                }
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) 
        {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) 
    {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}