import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        int[] pair = new int[n];

        for (int i = 0; i < n; i++) 
        {
            if (s.charAt(i) == '(') 
            {
                stack.push(i);
            } 
            else if (s.charAt(i) == ')') 
            {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int curr = 0;
        int dir = 1;

        while (curr < n) 
        {
            char c = s.charAt(curr);
            if (c == '(' || c == ')') 
            {
                curr = pair[curr];
                dir = -dir;
            } 
            else 
            {
                sb.append(c);
            }
            curr += dir;
        }

        return sb.toString();
    }
}