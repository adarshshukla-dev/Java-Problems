import java.util.Arrays;

class Solution {
    private static final long MAX_K = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) 
        {
            freq[c - 'a']++;
        }

        int[] halfFreq = new int[26];
        char middleChar = 0;
        int halfLen = 0;

        for (int i = 0; i < 26; i++) 
        {
            halfFreq[i] = freq[i] / 2;
            halfLen += halfFreq[i];
            if (freq[i] % 2 != 0) 
            {
                middleChar = (char) ('a' + i);
            }
        }

        long totalPerms = countArrangements(halfFreq);
        if (k > totalPerms) 
        {
            return "";
        }

        StringBuilder leftHalf = new StringBuilder();
        long currentK = k;

        for (int pos = 0; pos < halfLen; pos++) 
        {
            for (int i = 0; i < 26; i++) 
            {
                if (halfFreq[i] == 0) continue;

                halfFreq[i]--;
                long subPerms = countArrangements(halfFreq);

                if (subPerms >= currentK) 
                {
                    leftHalf.append((char) ('a' + i));
                    break;
                } 
                else 
                {
                    currentK -= subPerms;
                    halfFreq[i]++;
                }
            }
        }

        StringBuilder result = new StringBuilder(leftHalf);
        if (middleChar != 0) 
        {
            result.append(middleChar);
        }
        result.append(new StringBuilder(leftHalf).reverse());

        return result.toString();
    }

    private long countArrangements(int[] counts) 
    {
        int total = 0;
        for (int c : counts) total += c;

        long res = 1;
        for (int c : counts) 
        {
            if (c == 0) continue;
            res *= nCk(total, c);
            if (res >= MAX_K) return MAX_K;
            total -= c;
        }
        return res;
    }

    private long nCk(int n, int r) 
    {
        if (r > n - r) r = n - r;
        long res = 1;
        for (int i = 1; i <= r; i++) 
        {
            res = res * (n - i + 1) / i;
            if (res >= MAX_K) return MAX_K;
        }
        return res;
    }
}