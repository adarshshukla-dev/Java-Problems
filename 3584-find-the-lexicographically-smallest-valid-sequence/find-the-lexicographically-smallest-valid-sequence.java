class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[] last = new int[m];
        int p = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(j)) {
                p--;
            }
            last[j] = p;
            if (p >= 0) {
                p--;
            }
        }
        
        int[] seq = new int[m];
        int i = 0;
        int j = 0;
        boolean changed = false;
        
        while (i < n && j < m) {
            if (word1.charAt(i) == word2.charAt(j)) {
                seq[j] = i;
                j++;
            } else {
                if (!changed && (j + 1 == m || i + 1 <= last[j + 1])) {
                    seq[j] = i;
                    changed = true;
                    j++;
                }
            }
            i++;
        }
        
        if (j == m) {
            return seq;
        } else {
            return new int[0];
        }
    }
}