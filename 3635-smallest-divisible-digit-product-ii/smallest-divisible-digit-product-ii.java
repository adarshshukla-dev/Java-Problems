import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        long tempT = t;
        int req2 = 0, req3 = 0, req5 = 0, req7 = 0;

        while (tempT % 2 == 0) { req2++; tempT /= 2; }
        while (tempT % 3 == 0) { req3++; tempT /= 3; }
        while (tempT % 5 == 0) { req5++; tempT /= 5; }
        while (tempT % 7 == 0) { req7++; tempT /= 7; }

        if (tempT > 1) return "-1";

        int n = num.length();
        int[] digits = new int[n];
        int firstZero = -1;

        for (int i = 0; i < n; i++) 
        {
            digits[i] = num.charAt(i) - '0';
            if (digits[i] == 0 && firstZero == -1) 
            {
                firstZero = i;
            }
        }

        int limit = (firstZero == -1) ? n : firstZero;

        int[] p2 = new int[n + 1];
        int[] p3 = new int[n + 1];
        int[] p5 = new int[n + 1];
        int[] p7 = new int[n + 1];

        for (int i = 0; i < limit; i++) 
        {
            p2[i + 1] = p2[i] + countFactor(digits[i], 2);
            p3[i + 1] = p3[i] + countFactor(digits[i], 3);
            p5[i + 1] = p5[i] + countFactor(digits[i], 5);
            p7[i + 1] = p7[i] + countFactor(digits[i], 7);
        }

        for (int i = limit; i >= 0; i--) 
        {
            int rem2 = Math.max(0, req2 - p2[i]);
            int rem3 = Math.max(0, req3 - p3[i]);
            int rem5 = Math.max(0, req5 - p5[i]);
            int rem7 = Math.max(0, req7 - p7[i]);

            int startDigit;
            if (i == n) 
            {
                startDigit = 10; 
            } 
            else if (i < limit) 
            {
                startDigit = digits[i] + 1;
            } 
            else 
            {
                startDigit = Math.max(1, digits[i] + 1);
            }

            if (i == n) {
                if (rem2 == 0 && rem3 == 0 && rem5 == 0 && rem7 == 0) 
                {
                    return num;
                }
                continue;
            }

            for (int d = startDigit; d <= 9; d++) 
            {
                int r2 = Math.max(0, rem2 - countFactor(d, 2));
                int r3 = Math.max(0, rem3 - countFactor(d, 3));
                int r5 = Math.max(0, rem5 - countFactor(d, 5));
                int r7 = Math.max(0, rem7 - countFactor(d, 7));

                int remLen = n - 1 - i;
                int minDigitsNeeded = getMinDigits(r2, r3, r5, r7);

                if (minDigitsNeeded <= remLen) 
                {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) 
                    {
                        sb.append(digits[j]);
                    }
                    sb.append(d);
                    sb.append(buildMinSuffix(r2, r3, r5, r7, remLen));
                    return sb.toString();
                }
            }
        }

        int targetLen = n + 1;
        while (getMinDigits(req2, req3, req5, req7) > targetLen) 
        {
            targetLen++;
        }

        return buildMinSuffix(req2, req3, req5, req7, targetLen);
    }

    private int countFactor(int n, int p) {
        if (n == 0) return 0;
        int c = 0;
        while (n % p == 0) 
        {
            c++;
            n /= p;
        }
        return c;
    }

    private int getMinDigits(int r2, int r3, int r5, int r7) {
        int count = r5 + r7;
        int c9 = r3 / 2;
        int c3 = r3 % 2;
        int c8 = r2 / 3;
        int rem2 = r2 % 3;

        if (rem2 == 2 && c3 == 1) 
        {
            count += c8 + c9 + 2;
        } 
        else if (rem2 == 2) 
        {
            count += c8 + c9 + c3 + 1;
        } 
        else if (rem2 == 1 && c3 == 1) 
        {
            count += c8 + c9 + 1;
        } 
        else if (rem2 == 1) 
        {
            count += c8 + c9 + c3 + 1;
        } 
        else 
        {
            count += c8 + c9 + c3;
        }

        return count;
    }

    private String buildMinSuffix(int r2, int r3, int r5, int r7, int len) {
        int[] res = new int[len];
        Arrays.fill(res, 1);

        int idx = len - 1;
        while (r7 > 0) { res[idx--] = 7; r7--; }
        while (r5 > 0) { res[idx--] = 5; r5--; }

        while (r3 >= 2) { res[idx--] = 9; r3 -= 2; }
        while (r2 >= 3) { res[idx--] = 8; r2 -= 3; }

        if (r2 == 2 && r3 == 1) 
        {
            res[idx--] = 6;
            res[idx--] = 2;
            r2 = 0; r3 = 0;
        } 
        else if (r2 == 2) 
        {
            res[idx--] = 4;
            r2 = 0;
        } 
        else if (r3 == 1 && r2 == 1) 
        {
            res[idx--] = 6;
            r2 = 0; r3 = 0;
        } 
        else if (r3 == 1) 
        {
            res[idx--] = 3;
            r3 = 0;
        } 
        else if (r2 == 1) 
        {
            res[idx--] = 2;
            r2 = 0;
        }

        Arrays.sort(res);
        StringBuilder sb = new StringBuilder();
        for (int v : res) sb.append(v);
        return sb.toString();
    }
}