class Solution {
    public long countCommas(long n) {
        long commas = 0;
        long start = 1000L;

        while (n >= start) 
        {
            commas += (n - start + 1);
            if (start > Long.MAX_VALUE / 1000) 
            {
                break;
            }
            start *= 1000L;
        }
        return commas;
    }
}
