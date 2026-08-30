class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        int removeBothFromFront = right + 1;
        int removeBothFromBack = n - left;
        int removeOneFromFrontOneFromBack = (left + 1) + (n - right);

        return Math.min(removeBothFromFront, Math.min(removeBothFromBack, removeOneFromFrontOneFromBack));
    }
}