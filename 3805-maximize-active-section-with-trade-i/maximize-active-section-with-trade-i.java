import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') initialOnes++;
        }

        String t = "1" + s + "1";

        List<int[]> runs = new ArrayList<>(); 
        int n = t.length();
        int i = 0;

        while (i < n) {
            int j = i;
            while (j < n && t.charAt(j) == t.charAt(i)) {
                j++;
            }
            runs.add(new int[]{t.charAt(i) - '0', j - i});
            i = j;
        }

        int maxGain = 0;

        for (int k = 1; k < runs.size() - 1; k++) {
            if (runs.get(k)[0] == 1) {
                int leftZeros = runs.get(k - 1)[1];
                int rightZeros = runs.get(k + 1)[1];

                int gain = leftZeros + rightZeros;
                maxGain = Math.max(maxGain, gain);
            }
        }

        return initialOnes + maxGain;
    }
}