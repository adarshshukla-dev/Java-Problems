import java.util.*;

class Solution 
{
    private static final int MOD = 1_000_000_007;

    static class SegmentTree 
    {
        int n;
        long[] treeSum;
        long[] treeSqSum;
        long[] lazy;

        public SegmentTree(int n) 
        {
            this.n = n;
            treeSum = new long[4 * n];
            treeSqSum = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void pushDown(int node, int start, int end) {
            if (lazy[node] == 0) return;

            long val = lazy[node];
            int mid = start + (end - start) / 2;
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;

            apply(leftChild, start, mid, val);
            apply(rightChild, mid + 1, end, val);

            lazy[node] = 0;
        }

        private void apply(int node, int start, int end, long val) {
            long len = end - start + 1;
            treeSqSum[node] = (treeSqSum[node] + 2 * val % MOD * treeSum[node] % MOD + val * val % MOD * len % MOD) % MOD;
            treeSum[node] = (treeSum[node] + val * len % MOD) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        public void update(int node, int start, int end, int l, int r, long val) {
            if (l <= start && end <= r) 
            {
                apply(node, start, end, val);
                return;
            }

            pushDown(node, start, end);
            int mid = start + (end - start) / 2;

            if (l <= mid) update(2 * node, start, mid, l, r, val);
            if (r > mid) update(2 * node + 1, mid + 1, end, l, r, val);

            treeSum[node] = (treeSum[2 * node] + treeSum[2 * node + 1]) % MOD;
            treeSqSum[node] = (treeSqSum[2 * node] + treeSqSum[2 * node + 1]) % MOD;
        }
    }

    public int sumCounts(int[] nums) 
    {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        Map<Integer, Integer> lastPos = new HashMap<>();

        long totalSum = 0;

        for (int i = 0; i < n; i++) 
        {
            int prevIdx = lastPos.getOrDefault(nums[i], -1);
            st.update(1, 0, n - 1, prevIdx + 1, i, 1);
            totalSum = (totalSum + st.treeSqSum[1]) % MOD;
            lastPos.put(nums[i], i);
        }

        return (int) totalSum;
    }
}