class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        long totalPoints = n + k - 1;
        long totalChoices = 2 * k;

        long num = 1;
        long den = 1;

        for (int i = 0; i < totalChoices; i++) 
        {
            num = (num * (totalPoints - i)) % MOD;
            den = (den * (i + 1)) % MOD;
        }

        return (int) (num * modInverse(den, MOD) % MOD);
    }

    private long modInverse(long a, int m) 
    {
        return power(a, m - 2, m);
    }

    private long power(long x, long y, int m) {
        long res = 1;
        x = x % m;
        while (y > 0) 
        {
            if ((y & 1) == 1) 
            {
                res = (res * x) % m;
            }
            y >>= 1;
            x = (x * x) % m;
        }
        return res;
    }
}