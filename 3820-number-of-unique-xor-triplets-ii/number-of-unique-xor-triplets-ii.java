import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) 
    {
        boolean[] inS = new boolean[2048];
        List<Integer> S = new ArrayList<>();
        for (int x : nums) 
        {
            if (!inS[x]) 
            {
                inS[x] = true;
                S.add(x);
            }
        }

        boolean[] inP = new boolean[2048];
        List<Integer> P = new ArrayList<>();
        int sSize = S.size();
        for (int i = 0; i < sSize; i++) 
        {
            for (int j = i; j < sSize; j++) 
            {
                int pairXor = S.get(i) ^ S.get(j);
                if (!inP[pairXor]) 
                {
                    inP[pairXor] = true;
                    P.add(pairXor);
                }
            }
        }

        boolean[] inT = new boolean[2048];
        int uniqueTripletsCount = 0;
        for (int p : P) 
        {
            for (int c : S) 
            {
                int tripletXor = p ^ c;
                if (!inT[tripletXor]) 
                {
                    inT[tripletXor] = true;
                    uniqueTripletsCount++;
                }
            }
        }

        return uniqueTripletsCount;
    }
}