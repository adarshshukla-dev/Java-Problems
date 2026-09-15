class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int count = 0;
        int lastEnd = -1;

        for (int center = 0; center < 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + (center % 2);

            while (left >= 0 && right < n && arr[left] == arr[right]) {
                int len = right - left + 1;
                if (len >= k) {
                    if (left > lastEnd) {
                        count++;
                        lastEnd = right;
                    }
                    break;
                }
                left--;
                right++;
            }
        }

        return count;
    }
}