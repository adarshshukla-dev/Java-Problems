import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String decodeString(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) 
        {
            if (Character.isDigit(ch)) 
            {
                k = k * 10 + (ch - '0');
            }
            else if (ch == '[') 
            {
                countStack.push(k);
                stringStack.push(currentString);
                
                currentString = new StringBuilder();
                k = 0;
            } 
            else if (ch == ']') 
            {
                StringBuilder decodedString = stringStack.pop();
                int count = countStack.pop();
                
                while (count-- > 0) 
                {
                    decodedString.append(currentString);
                }
                
                currentString = decodedString;
            } 
            else 
            {
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }
}