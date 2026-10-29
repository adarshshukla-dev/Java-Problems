import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] parent, depth, heavy, head, pos;
    private int curPos;
    private int[] tree;
    private int[] nodeMask;
    private char[] chars;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        chars = s.toCharArray();
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        head = new int[n];
        pos = new int[n];
        nodeMask = new int[n];

        dfs1(0, -1, 0);
        curPos = 0;
        dfs2(0, 0);

        tree = new int[4 * n];
        for (int i = 0; i < n; i++) {
            nodeMask[pos[i]] = 1 << (chars[i] - 'a');
        }
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();
        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                chars[u] = c;
                int newMask = 1 << (c - 'a');
                update(1, 0, n - 1, pos[u], newMask);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathMask = queryPath(u, v);
                ans.add(pathMask == 0 || (pathMask & (pathMask - 1)) == 0);
            }
        }
        return ans;
    }

    private int dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        int size = 1;
        int maxSubtree = 0;
        for (int v : adj[u]) {
            if (v != p) {
                int sub = dfs1(v, u, d + 1);
                size += sub;
                if (sub > maxSubtree) {
                    maxSubtree = sub;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = nodeMask[l];
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(node * 2, l, mid, idx, val);
        else update(node * 2 + 1, mid + 1, r, idx, val);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private int querySegment(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;
        return querySegment(node * 2, l, mid, ql, qr) ^ querySegment(node * 2 + 1, mid + 1, r, ql, qr);
    }

    private int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= querySegment(1, 0, chars.length - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= querySegment(1, 0, chars.length - 1, pos[u], pos[v]);
        return res;
    }
}