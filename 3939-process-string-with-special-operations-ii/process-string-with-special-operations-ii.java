class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] lengths = new long[n];
        long len = 0;

        for (int i = 0; i < n; i++) 
        {
            char c = s.charAt(i);
            if (c == '*') 
            {
                len = Math.max(0, len - 1);
            } 
            else if (c == '#') 
            {
                len = len * 2;
            } 
            else if (c == '%') 
            {
            } 
            else 
            {
                len++;
            }
            lengths[i] = len;
        }

        if (k < 0 || k >= len) 
        {
            return '.';
        }
        for (int i = n - 1; i >= 0; i--) 
        {
            char c = s.charAt(i);
            long prevLen = (i > 0) ? lengths[i - 1] : 0;

            if (c == '*') 
            {
            } else if (c == '#') 
            {
                if (k >= prevLen) 
                {
                    k -= prevLen;
                }
            } 
            else if (c == '%') 
            {
                k = lengths[i] - 1 - k;
            } 
            else 
            {
                if (k == prevLen) 
                {
                    return c;
                }
            }
        }

        return '.';
    }
}