import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : invocations) adj.get(e[0]).add(e[1]);

        boolean[] bad = new boolean[n];
        Queue<Integer> q = new LinkedList<>(List.of(k));
        bad[k] = true;
        while (!q.isEmpty()) {
            for (int next : adj.get(q.poll())) {
                if (!bad[next] && (bad[next] = true)) q.add(next);
            }
        }

        for (int[] e : invocations) {
            if (!bad[e[0]] && bad[e[1]]) bad = new boolean[n];
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) if (!bad[i]) ans.add(i);
        return ans;
        
    }
}