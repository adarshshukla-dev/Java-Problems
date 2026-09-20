class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) 
        {
            int charVal = 26 - (s.charAt(i) - 'a');
            total += charVal * (i + 1);
        }
        return total;
        
    }
}