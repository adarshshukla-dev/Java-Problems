class Solution {
    static class SegmentTree {
        int n, k;
        int[][][] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new int[4 * n][k][k + 1];
            build(1, 0, n - 1, nums);
        }

        private void combine(int[][] left, int[][] right, int[][] res) {
            for (int entry = 0; entry < k; entry++) {
                int leftProdMod = left[entry][k];
                int rightProdMod = right[leftProdMod][k];
                res[entry][k] = rightProdMod;

                for (int m = 0; m < k; m++) {
                    res[entry][m] = left[entry][m];
                }

                for (int m = 0; m < k; m++) {
                    res[entry][m] += right[leftProdMod][m];
                }
            }
        }

        private void build(int node, int start, int end, int[] nums) {
            if (start == end) {
                int elemMod = nums[start] % k;
                for (int entry = 0; entry < k; entry++) {
                    int prod = (entry * elemMod) % k;
                    tree[node][entry][prod] = 1;
                    tree[node][entry][k] = prod;
                }
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node, start, mid, nums);
            build(2 * node + 1, mid + 1, end, nums);
            combine(tree[2 * node], tree[2 * node + 1], tree[node]);
        }

        public void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                int elemMod = val % k;
                tree[node] = new int[k][k + 1];
                for (int entry = 0; entry < k; entry++) {
                    int prod = (entry * elemMod) % k;
                    tree[node][entry][prod] = 1;
                    tree[node][entry][k] = prod;
                }
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            combine(tree[2 * node], tree[2 * node + 1], tree[node]);
        }

        public int[][] query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return null;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            int[][] left = query(2 * node, start, mid, l, r);
            int[][] right = query(2 * node + 1, mid + 1, end, l, r);

            if (left == null) return right;
            if (right == null) return left;

            int[][] res = new int[k][k + 1];
            combine(left, right, res);
            return res;
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        SegmentTree segTree = new SegmentTree(nums, k);
        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            segTree.update(1, 0, nums.length - 1, idx, val);

            int[][] res = segTree.query(1, 0, nums.length - 1, start, nums.length - 1);
            ans[i] = res != null ? res[1 % k][x] : 0;
        }

        return ans;
    }
}