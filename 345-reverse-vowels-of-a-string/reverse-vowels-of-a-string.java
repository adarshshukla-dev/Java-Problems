import java.util.regex.*;

class Solution {
    public String reverseVowels(String s) {
        String vowels = s.replaceAll("(?i)[^aeiou]", "");
        int[] p = {vowels.length() - 1};

        return Pattern.compile("(?i)[aeiou]").matcher(s)
                      .replaceAll(m -> String.valueOf(vowels.charAt(p[0]--)));
    }
}