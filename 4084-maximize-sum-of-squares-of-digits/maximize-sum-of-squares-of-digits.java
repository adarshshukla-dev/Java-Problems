class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) return "";

        StringBuilder sb = new StringBuilder();
        int remainingSum = sum;

        for (int i = 0; i < num; i++) 
        {
            int digit = Math.min(9, remainingSum);
            sb.append(digit);
            remainingSum -= digit;
        }

        return sb.toString();
    }
}