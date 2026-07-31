class Solution {
    public int maxVowels(String s, int k) {
        int max = 0, count = 0;
        
        for (int i = 0; i < s.length(); i++) 
        {
            if (isVowel(s.charAt(i))) count++;
            
            if (i >= k && isVowel(s.charAt(i - k))) count--;
            
            if (count > max) max = count;
        }
        
        return max;
    }

    private boolean isVowel(char c) 
    {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}